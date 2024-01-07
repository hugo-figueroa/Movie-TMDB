package com.example.simplemovieapp.features.movieLists.presentation.viewModel

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.example.core.base.viewModel.BaseViewModel
import com.example.core.extensionFunctions.logInfo
import com.example.core.models.Result
import com.example.simplemovieapp.features.movieLists.domain.models.PopularMoviesDomain
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
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : BaseViewModel() {

    private var moviesPage = 0
    override fun setUp(bundle: Bundle?) {
        super.setUp(bundle)
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        moviesPage += 1
        viewModelScope.launch {
            when (val resultCall =
                withContext(Dispatchers.IO) { getPopularMoviesUseCase(moviesPage) }) {
                is Result.Success<PopularMoviesDomain> -> {
                    logInfo(resultCall.data.results[0].toString())
                }

                is Result.Error -> {

                }
            }
        }
    }

}