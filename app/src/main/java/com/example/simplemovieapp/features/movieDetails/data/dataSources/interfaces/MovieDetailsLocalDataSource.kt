package com.example.simplemovieapp.features.movieDetails.data.dataSources.interfaces

import com.example.core.models.Result
import com.example.simplemovieapp.features.movieDetails.domain.models.MovieDetailsDomain

/**
 * MovieDetailsLocalDataSource
 *
 * @author (c) 2024, Hugo Figueroa
 * */
interface MovieDetailsLocalDataSource {
    suspend fun saveMovieToFavorites(movie: MovieDetailsDomain): Result<Unit>
    suspend fun removeMovieFromFavorites(movieId: Int): Result<Unit>
    suspend fun isMovieOnFavorites(movieId: Int): Result<Boolean>
}