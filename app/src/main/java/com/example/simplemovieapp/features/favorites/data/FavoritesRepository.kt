package com.example.simplemovieapp.features.favorites.data

import com.example.core.models.Result
import com.example.simplemovieapp.features.favorites.data.dataSources.interfaces.FavoritesLocalDataSource
import com.example.simplemovieapp.features.favorites.domain.models.MovieDetailsDomain
import javax.inject.Inject

/**
 * FavoritesRepository
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class FavoritesRepository @Inject constructor(
    private val favoritesLocalDataSource: FavoritesLocalDataSource
) {
    suspend fun getFavoritesMovies(): Result<List<MovieDetailsDomain>> =
        favoritesLocalDataSource.getFavoritesMovies()

    suspend fun removeMovieFromFavorites(movieId: Int): Result<Unit> =
        favoritesLocalDataSource.removeMovieFromFavorites(movieId)
}