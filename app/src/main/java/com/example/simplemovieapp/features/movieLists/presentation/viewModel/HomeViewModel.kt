package com.example.simplemovieapp.features.movieLists.presentation.viewModel

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.example.core.base.viewModel.BaseViewModel
import com.example.core.extensionFunctions.logInfo
import com.example.core.models.Result
import com.example.simplemovieapp.features.movieLists.domain.models.ListMoviesDomain
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

    private var popularMoviesPage = 0
    private var nowPlayingMoviesPage = 0
    override fun setUp(bundle: Bundle?) {
        super.setUp(bundle)
        fetchNowPlayingMovies()
    }

    private fun fetchPopularMovies() {
        popularMoviesPage += 1
        viewModelScope.launch {
            when (val resultCall =
                withContext(Dispatchers.IO) { getPopularMoviesUseCase(popularMoviesPage) }) {
                is Result.Success<ListMoviesDomain> -> {
                    logInfo(resultCall.data.results[0].toString())
                }

                is Result.Error -> {

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
                    logInfo(resultCall.data.results[0].toString())
                }

                is Result.Error -> {

                }
            }
        }
    }

}