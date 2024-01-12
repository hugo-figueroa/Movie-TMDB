package com.example.simplemovieapp.features.movieLists.presentation.nowPlayingMovies.viewModel

import android.os.Bundle
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.core.base.viewModel.BaseViewModel
import com.example.core.models.Result
import com.example.core.utils.SingleLiveEvent
import com.example.simplemovieapp.features.movieLists.domain.models.ListMoviesDomain
import com.example.simplemovieapp.features.movieLists.domain.models.MovieDomain
import com.example.simplemovieapp.features.movieLists.domain.useCases.GetBaseImageUrlUseCase
import com.example.simplemovieapp.features.movieLists.domain.useCases.GetNowPlayingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * NowPlayingMoviesViewModel
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@HiltViewModel
class NowPlayingMoviesViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getBaseImageUrlUseCase: GetBaseImageUrlUseCase
) : BaseViewModel() {

    private var nowPlayingMoviesMLD: SingleLiveEvent<NowPlayingMoviesUiState> = SingleLiveEvent()
    val nowPlayingMovies: LiveData<NowPlayingMoviesUiState> = nowPlayingMoviesMLD

    var nowPlayingMoviesList = mutableListOf<MovieDomain>()
    var nowPlayingMoviesGrid = mutableListOf<MovieDomain>()

    var baseImageUrl = ""
    var movieId = 0
    var nowPlayingMoviesPageList = 1
    var nowPlayingMoviesPageGrid = 1

    override fun setUp(bundle: Bundle?) {
        super.setUp(bundle)
        getBaseImageUrl()
    }

    fun getBaseImageUrl() {
        viewModelScope.launch {
            baseImageUrl = withContext(Dispatchers.IO) { getBaseImageUrlUseCase() }
            nowPlayingMoviesMLD.value = NowPlayingMoviesUiState.Loading
            fetchNowPlayingMoviesList(1)
        }
    }

    fun fetchNowPlayingMoviesList(page: Int) {
        viewModelScope.launch {
            when (val resultCall =
                withContext(Dispatchers.IO) { getNowPlayingMoviesUseCase(page) }) {
                is Result.Success<ListMoviesDomain> -> {
                    nowPlayingMoviesList.addAll(resultCall.data.results)
                    if (page == 1) nowPlayingMoviesGrid.addAll(resultCall.data.results)

                    nowPlayingMoviesMLD.value =
                        NowPlayingMoviesUiState.Content(resultCall.data.results.toMutableStateList())
                }

                is Result.Error -> {
                    nowPlayingMoviesMLD.value =
                        NowPlayingMoviesUiState.Error(resultCall.throwable)
                }
            }
        }
    }

    fun fetchNowPlayingMoviesGrid(page: Int) {
        viewModelScope.launch {
            when (val resultCall =
                withContext(Dispatchers.IO) { getNowPlayingMoviesUseCase(page) }) {
                is Result.Success<ListMoviesDomain> -> {
                    nowPlayingMoviesGrid.addAll(resultCall.data.results)
                    nowPlayingMoviesMLD.value =
                        NowPlayingMoviesUiState.Content(resultCall.data.results.toMutableStateList())
                }

                is Result.Error -> {
                    nowPlayingMoviesMLD.value =
                        NowPlayingMoviesUiState.Error(resultCall.throwable)
                }
            }
        }
    }

    fun goToMovieDetails(movieId: Int) {
        this.movieId = movieId
        onNavigationEventMLD.value = Unit
    }
}

sealed interface NowPlayingMoviesUiState {
    object Loading : NowPlayingMoviesUiState
    data class Content(val data: SnapshotStateList<MovieDomain>) : NowPlayingMoviesUiState
    data class Error(val error: Throwable) : NowPlayingMoviesUiState
}