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
import com.example.simplemovieapp.features.movieLists.domain.useCases.GetBaseImageUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
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
    private val getBaseImageUrlUseCase: GetBaseImageUrlUseCase
) : BaseViewModel() {

    private var movieDetailsMLD: SingleLiveEvent<MovieDetailsUiState> = SingleLiveEvent()
    val movieDetails: LiveData<MovieDetailsUiState> = movieDetailsMLD

    var baseImageUrl = ""

    override fun setUp(bundle: Bundle?) {
        super.setUp(bundle)
        val movieId = bundle?.getInt(Constants.MOVIE_ID)
        movieId?.let {
            getBaseImageUrl(it)
            getMovieDetailsById(it)
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
                    movieDetailsMLD.value = MovieDetailsUiState.Content(resultCall.data)
                }

                is Result.Error -> {
                    movieDetailsMLD.value = MovieDetailsUiState.Error(resultCall.throwable)
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