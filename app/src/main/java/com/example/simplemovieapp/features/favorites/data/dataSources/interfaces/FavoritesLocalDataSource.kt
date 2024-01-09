package com.example.simplemovieapp.features.favorites.data.dataSources.interfaces

import com.example.core.models.Result
import com.example.simplemovieapp.features.favorites.domain.models.MovieDetailsDomain

/**
 * FavoritesLocalDataSource
 *
 * @author (c) 2024, Hugo Figueroa
 * */
interface FavoritesLocalDataSource {
    suspend fun getFavoritesMovies(): Result<List<MovieDetailsDomain>>
    suspend fun removeMovieFromFavorites(movieId: Int): Result<Unit>
}