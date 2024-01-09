package com.example.simplemovieapp.features.favorites.presentation.viewModels

import android.os.Bundle
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.core.base.viewModel.BaseViewModel
import com.example.core.models.Result
import com.example.core.utils.SingleLiveEvent
import com.example.simplemovieapp.features.favorites.domain.models.MovieDetailsDomain
import com.example.simplemovieapp.features.favorites.domain.useCases.GetFavoritesMoviesUseCase
import com.example.simplemovieapp.features.movieLists.domain.useCases.GetBaseImageUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * FavoritesViewModel
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesMoviesUseCase: GetFavoritesMoviesUseCase,
    private val getBaseImageUrlUseCase: GetBaseImageUrlUseCase
) : BaseViewModel() {

    private var favoriteMoviesMLD: SingleLiveEvent<FavoritesUiState> = SingleLiveEvent()
    val favoriteMovies: LiveData<FavoritesUiState> = favoriteMoviesMLD

    var favoriteMoviesList = mutableListOf<MovieDetailsDomain>()
    var favoriteMoviesGrid = mutableListOf<MovieDetailsDomain>()

    var baseImageUrl = ""
    override fun setUp(bundle: Bundle?) {
        super.setUp(bundle)
        getBaseImageUrl()
    }

    private fun getBaseImageUrl() {
        viewModelScope.launch {
            baseImageUrl = withContext(Dispatchers.IO) { getBaseImageUrlUseCase() }
            favoriteMoviesMLD.value = FavoritesUiState.Loading
            fetchFavoritesList(1)
        }
    }

    fun fetchFavoritesList(page: Int) {
        viewModelScope.launch {
            when (val resultCall =
                withContext(Dispatchers.IO) { getFavoritesMoviesUseCase() }) {
                is Result.Success<List<MovieDetailsDomain>> -> {
                    favoriteMoviesList.addAll(resultCall.data)
                    if (page == 1) favoriteMoviesGrid.addAll(resultCall.data)
                    favoriteMoviesMLD.value =
                        FavoritesUiState.Content(resultCall.data.toMutableStateList())
                }

                is Result.Error -> {

                }
            }
        }
    }

    fun fetchFavoritesGrid(page: Int) {
        viewModelScope.launch {
            when (val resultCall =
                withContext(Dispatchers.IO) { getFavoritesMoviesUseCase() }) {
                is Result.Success<List<MovieDetailsDomain>> -> {
                    favoriteMoviesGrid.addAll(resultCall.data)
                    favoriteMoviesMLD.value =
                        FavoritesUiState.Content(resultCall.data.toMutableStateList())
                }

                is Result.Error -> {

                }
            }
        }
    }
}

sealed interface FavoritesUiState {
    object Loading : FavoritesUiState
    data class Content(val data: SnapshotStateList<MovieDetailsDomain>) : FavoritesUiState
    data class Error(val error: Throwable) : FavoritesUiState
}