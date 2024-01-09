package com.example.simplemovieapp.features.movieLists.presentation.viewModel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.base.viewModel.BaseViewModel
import com.example.core.extensionFunctions.logInfo
import com.example.core.models.Result
import com.example.networking.exceptions.NoInternetException
import com.example.networking.exceptions.NotFoundException
import com.example.networking.exceptions.ServerErrorException
import com.example.networking.exceptions.UnknownErrorException
import com.example.simplemovieapp.features.movieLists.domain.models.ListMoviesDomain
import com.example.simplemovieapp.features.movieLists.domain.models.MovieDomain
import com.example.simplemovieapp.features.movieLists.domain.useCases.GetNowPlayingMoviesUseCase
import com.example.simplemovieapp.features.movieLists.domain.useCases.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * HomeViewModel
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
) : BaseViewModel() {

    private var movieListMLD: MutableLiveData<HomeUiState> = MutableLiveData()
    val movieList: LiveData<HomeUiState> = movieListMLD

    private var popularMoviesPage = 0
    private var nowPlayingMoviesPage = 0
    override fun setUp(bundle: Bundle?) {
        super.setUp(bundle)
        movieListMLD.value = HomeUiState.Loading
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        popularMoviesPage += 1
        viewModelScope.launch {
            when (val resultCall =
                withContext(Dispatchers.IO) { getPopularMoviesUseCase(popularMoviesPage) }) {
                is Result.Success<ListMoviesDomain> -> {
                    movieListMLD.value = HomeUiState.Content(resultCall.data.results)
                    logInfo(resultCall.data.results[0].toString())
                }

                is Result.Error -> {
                    when (resultCall.throwable) {
                        is NoInternetException -> {
                            // Internet Error
                        }

                        is NotFoundException -> {
                            // Not Found Information
                        }

                        is ServerErrorException -> {
                            // Server Error
                        }

                        is UnknownErrorException -> {
                            // Unknown Error
                        }

                        else -> {
                            // Unknown Error
                        }
                    }
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
                    movieListMLD.value = HomeUiState.Content(resultCall.data.results)
                    logInfo(resultCall.data.results[0].toString())
                }

                is Result.Error -> {
                    when (resultCall.throwable) {
                        is NoInternetException -> {
                            // Internet Error
                            movieListMLD.value =
                                HomeUiState.Error("We have problems with your internet connection")
                        }

                        is NotFoundException -> {
                            // Not Found Information
                            movieListMLD.value =
                                HomeUiState.Error("We are having problems, try later please")
                        }

                        is ServerErrorException -> {
                            // Server Error
                            movieListMLD.value =
                                HomeUiState.Error("We are having problems, try later please")
                        }

                        is UnknownErrorException -> {
                            // Unknown Error
                            movieListMLD.value =
                                HomeUiState.Error("We are having problems, try later please")
                        }

                        else -> {
                            // Unknown Error
                            movieListMLD.value =
                                HomeUiState.Error("We are having problems, try later please")
                        }
                    }
                }
            }
        }
    }
}

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Content(val data: List<MovieDomain>) : HomeUiState
    data class Error(val error: String) : HomeUiState
}