package com.example.simplemovieapp.features.movieLists.presentation.popularMovies.viewModel

import android.os.Bundle
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.core.base.viewModel.BaseViewModel
import com.example.core.models.NavigationEvent
import com.example.core.models.Result
import com.example.core.utils.SingleLiveEvent
import com.example.simplemovieapp.R
import com.example.simplemovieapp.features.movieLists.domain.models.ListMoviesDomain
import com.example.simplemovieapp.features.movieLists.domain.models.MovieDomain
import com.example.simplemovieapp.features.movieLists.domain.useCases.GetBaseImageUrlUseCase
import com.example.simplemovieapp.features.movieLists.domain.useCases.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * PopularMoviesViewModel
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getBaseImageUrlUseCase: GetBaseImageUrlUseCase
) : BaseViewModel() {

    private var popularMoviesMLD: SingleLiveEvent<PopularMoviesUiState> = SingleLiveEvent()
    val popularMovies: LiveData<PopularMoviesUiState> = popularMoviesMLD

    var popularMoviesList = mutableListOf<MovieDomain>()
    var popularMoviesGrid = mutableListOf<MovieDomain>()

    var baseImageUrl = ""
    var movieId = 0
    var popularMoviesPageList = 1
    var popularMoviesPageGrid = 1

    override fun setUp(bundle: Bundle?) {
        super.setUp(bundle)
        getBaseImageUrl()
    }

    private fun getBaseImageUrl() {
        viewModelScope.launch {
            baseImageUrl = withContext(Dispatchers.IO) { getBaseImageUrlUseCase() }
            popularMoviesMLD.value = PopularMoviesUiState.Loading
            fetchPopularMoviesList(1)
        }
    }

    fun fetchPopularMoviesList(page: Int) {
        viewModelScope.launch {
            when (val resultCall =
                withContext(Dispatchers.IO) { getPopularMoviesUseCase(page) }) {
                is Result.Success<ListMoviesDomain> -> {
                    popularMoviesList.addAll(resultCall.data.results)
                    if (page == 1) popularMoviesGrid.addAll(resultCall.data.results)
                    popularMoviesMLD.value =
                        PopularMoviesUiState.Content(resultCall.data.results.toMutableStateList())
                }

                is Result.Error -> {
                    popularMoviesMLD.value = PopularMoviesUiState.Error(resultCall.throwable)
                }
            }
        }
    }

    fun fetchPopularMoviesGrid(page: Int) {
        viewModelScope.launch {
            when (val resultCall =
                withContext(Dispatchers.IO) { getPopularMoviesUseCase(page) }) {
                is Result.Success<ListMoviesDomain> -> {
                    popularMoviesGrid.addAll(resultCall.data.results)
                    popularMoviesMLD.value =
                        PopularMoviesUiState.Content(resultCall.data.results.toMutableStateList())
                }

                is Result.Error -> {
                    popularMoviesMLD.value = PopularMoviesUiState.Error(resultCall.throwable)
                }
            }
        }
    }

    fun goToMovieDetails(movieId: Int) {
        this.movieId = movieId
        onNavigationEventMLD.value =
            NavigationEvent(R.id.action_popularMoviesFragment_to_movieDetailsFragment)
    }
}

sealed interface PopularMoviesUiState {
    object Loading : PopularMoviesUiState
    data class Content(val data: SnapshotStateList<MovieDomain>) : PopularMoviesUiState
    data class Error(val error: Throwable) : PopularMoviesUiState
}