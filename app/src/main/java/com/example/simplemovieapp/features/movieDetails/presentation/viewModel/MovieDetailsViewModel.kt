package com.example.simplemovieapp.features.movieDetails.presentation.viewModel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.core.base.viewModel.BaseViewModel
import com.example.core.models.Result
import com.example.core.utils.SingleLiveEvent
import com.example.simplemovieapp.constants.Constants
import com.example.simplemovieapp.features.movieDetails.domain.models.MovieDetailsDomain
import com.example.simplemovieapp.features.movieDetails.domain.useCases.GetMovieDetailsUseCase
import com.example.simplemovieapp.features.movieDetails.domain.useCases.IsMovieOnFavoritesUseCase
import com.example.simplemovieapp.features.movieDetails.domain.useCases.RemoveMovieFromFavoritesUseCase
import com.example.simplemovieapp.features.movieDetails.domain.useCases.SaveMovieToFavoritesUseCase
import com.example.simplemovieapp.features.movieLists.domain.useCases.GetBaseImageUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * MovieDetailViewModel
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getBaseImageUrlUseCase: GetBaseImageUrlUseCase,
    private val isMovieOnFavoritesUseCase: IsMovieOnFavoritesUseCase,
    private val saveMovieToFavoritesUseCase: SaveMovieToFavoritesUseCase,
    private val removeMovieFromFavoritesUseCase: RemoveMovieFromFavoritesUseCase
) : BaseViewModel() {

    private var movieDetailsMLD: SingleLiveEvent<MovieDetailsUiState> = SingleLiveEvent()
    val movieDetails: LiveData<MovieDetailsUiState> = movieDetailsMLD

    lateinit var movie: MovieDetailsDomain

    var baseImageUrl = ""
    var isInFavorites: Boolean? = null

    override fun setUp(bundle: Bundle?) {
        super.setUp(bundle)
        val movieId = bundle?.getInt(Constants.MOVIE_ID)
        movieId?.let {
            isMovieOnFavorites(movieId)
        }
    }

    private fun getBaseImageUrl(movieId: Int) {
        viewModelScope.launch {
            baseImageUrl = withContext(Dispatchers.IO) { getBaseImageUrlUseCase() }
            movieDetailsMLD.value = MovieDetailsUiState.Loading
            getMovieDetailsById(movieId)
        }
    }

    private fun getMovieDetailsById(movieId: Int) {
        viewModelScope.launch {
            when (val resultCall =
                withContext(Dispatchers.IO) { getMovieDetailsUseCase(movieId) }) {
                is Result.Success<MovieDetailsDomain> -> {
                    movie = resultCall.data
                    movieDetailsMLD.value = MovieDetailsUiState.Content(movie)
                }

                is Result.Error -> {
                    movieDetailsMLD.value = MovieDetailsUiState.Error(resultCall.throwable)
                }
            }
        }
    }

    private fun isMovieOnFavorites(movieId: Int) {
        movieDetailsMLD.value = MovieDetailsUiState.Loading
        viewModelScope.launch {
            when (val resultCall =
                withContext(Dispatchers.IO) { isMovieOnFavoritesUseCase(movieId) }) {
                is Result.Success<Boolean> -> {
                    isInFavorites = resultCall.data
                    getBaseImageUrl(movieId)
                }

                is Result.Error -> {
                    isInFavorites = null
                    getBaseImageUrl(movieId)
                }
            }
        }
    }

    fun saveMovieToFavorites(movie: MovieDetailsDomain) {
        movieDetailsMLD.value = MovieDetailsUiState.Loading
        viewModelScope.launch {
            when (withContext(Dispatchers.IO) { saveMovieToFavoritesUseCase(movie) }) {
                is Result.Success<Unit> -> {
                    isInFavorites = true
                    movieDetailsMLD.value = MovieDetailsUiState.Content(movie)
                }

                is Result.Error -> {
                    isInFavorites = false
                    movieDetailsMLD.value = MovieDetailsUiState.Content(movie)
                }
            }
        }
    }

    fun removeMovieToFavorites(movieId: Int) {
        movieDetailsMLD.value = MovieDetailsUiState.Loading
        viewModelScope.launch {
            when (withContext(Dispatchers.IO) { removeMovieFromFavoritesUseCase(movieId) }) {
                is Result.Success<Unit> -> {
                    isInFavorites = false
                    movieDetailsMLD.value = MovieDetailsUiState.Content(movie)
                }

                is Result.Error -> {
                    isInFavorites = true
                    movieDetailsMLD.value = MovieDetailsUiState.Content(movie)
                }
            }
        }
    }
}

sealed interface MovieDetailsUiState {
    object Loading : MovieDetailsUiState
    data class Content(val data: MovieDetailsDomain) : MovieDetailsUiState
    data class Error(val error: Throwable) : MovieDetailsUiState
}