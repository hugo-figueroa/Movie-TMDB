package com.example.simplemovieapp.features.movieDetails.presentation.viewModel

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.example.core.base.viewModel.BaseViewModel
import com.example.core.extensionFunctions.logInfo
import com.example.core.models.Result
import com.example.simplemovieapp.constants.Constants
import com.example.simplemovieapp.features.movieDetails.domain.models.MovieDetailsDomain
import com.example.simplemovieapp.features.movieDetails.domain.useCases.GetMovieDetailsUseCase
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
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : BaseViewModel() {

    override fun setUp(bundle: Bundle?) {
        super.setUp(bundle)
        val movieId = bundle?.getInt(Constants.MOVIE_ID)
        movieId?.let {
            getMovieDetailsById(it)
        }
    }

    private fun getMovieDetailsById(movieId: Int) {
        viewModelScope.launch {
            when (val resultCall =
                withContext(Dispatchers.IO) { getMovieDetailsUseCase(movieId) }) {
                is Result.Success<MovieDetailsDomain> -> {
                    logInfo(resultCall.data.toString())
                }

                is Result.Error -> {

                }
            }
        }
    }
}