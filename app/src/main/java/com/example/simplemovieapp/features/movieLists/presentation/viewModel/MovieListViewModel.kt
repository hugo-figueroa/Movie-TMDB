package com.example.simplemovieapp.features.movieLists.presentation.viewModel

import android.os.Bundle
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.core.base.viewModel.BaseViewModel
import com.example.core.extensionFunctions.logInfo
import com.example.core.models.Result
import com.example.core.utils.SingleLiveEvent
import com.example.simplemovieapp.features.movieLists.domain.models.ListMoviesDomain
import com.example.simplemovieapp.features.movieLists.domain.models.MovieDomain
import com.example.simplemovieapp.features.movieLists.domain.useCases.GetBaseImageUrlUseCase
import com.example.simplemovieapp.features.movieLists.domain.useCases.GetNowPlayingMoviesUseCase
import com.example.simplemovieapp.features.movieLists.domain.useCases.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * MovieListViewModel
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getBaseImageUrlUseCase: GetBaseImageUrlUseCase
) : BaseViewModel() {

    private var popularMovieListMLD: SingleLiveEvent<MovieListUiState> = SingleLiveEvent()
    val popularMovieList: LiveData<MovieListUiState> = popularMovieListMLD

    var popularMoviesList = mutableListOf<MovieDomain>()

    var baseImageUrl = ""
    var popularMoviesPage = 1
    private var nowPlayingMoviesPage = 1
    override fun setUp(bundle: Bundle?) {
        super.setUp(bundle)
        popularMovieListMLD.value = MovieListUiState.Loading
        getBaseImageUrl()
    }

    private fun getBaseImageUrl() {
        viewModelScope.launch {
            baseImageUrl = withContext(Dispatchers.IO) { getBaseImageUrlUseCase() }
            fetchPopularMovies(1)
        }
    }

    fun fetchPopularMovies(page: Int) {
        viewModelScope.launch {
            when (val resultCall =
                withContext(Dispatchers.IO) { getPopularMoviesUseCase(page) }) {
                is Result.Success<ListMoviesDomain> -> {
                    popularMoviesList.addAll(resultCall.data.results)
                    popularMovieListMLD.value =
                        MovieListUiState.Content(resultCall.data.results.toMutableStateList())
                    logInfo(resultCall.data.results[0].toString())
                }

                is Result.Error -> {
                    popularMovieListMLD.value = MovieListUiState.Error(resultCall.throwable)
                }
            }
        }
    }

    private fun fetchNowPlayingMovies() {
        nowPlayingMoviesPage += 1
        viewModelScope.launch {
            when (val resultCall =
                withContext(Dispatchers.IO) { getNowPlayingMoviesUseCase(nowPlayingMoviesPage) }) {
                is Result.Success<ListMoviesDomain> -> {
                    popularMovieListMLD.value =
                        MovieListUiState.Content(resultCall.data.results.toMutableStateList())
                    logInfo(resultCall.data.results[0].toString())
                }

                is Result.Error -> {
                    popularMovieListMLD.value = MovieListUiState.Error(resultCall.throwable)
                }
            }
        }
    }
}

sealed interface MovieListUiState {
    object Loading : MovieListUiState
    data class Content(val data: SnapshotStateList<MovieDomain>) : MovieListUiState
    data class Error(val error: Throwable) : MovieListUiState
}