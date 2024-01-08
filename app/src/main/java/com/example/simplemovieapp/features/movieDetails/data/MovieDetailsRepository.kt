package com.example.simplemovieapp.features.movieDetails.data

import com.example.core.models.Result
import com.example.simplemovieapp.features.movieDetails.data.dataSources.interfaces.MovieDetailsLocalDataSource
import com.example.simplemovieapp.features.movieDetails.data.dataSources.interfaces.MovieDetailsRemoteDataSource
import com.example.simplemovieapp.features.movieDetails.domain.models.MovieDetailsDomain
import javax.inject.Inject

/**
 * MovieDetailsRepository
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class MovieDetailsRepository @Inject constructor(
    private val movieDetailsRemoteDataSource: MovieDetailsRemoteDataSource,
    private val movieDetailsLocalDataSource: MovieDetailsLocalDataSource
) {
    suspend fun getMovieDetailsById(movieId: Int): Result<MovieDetailsDomain> =
        movieDetailsRemoteDataSource.getMovieDetailsById(movieId)

    suspend fun saveMovieToFavorites(movie: MovieDetailsDomain): Result<Unit> =
        movieDetailsLocalDataSource.saveMovieToFavorites(movie)

    suspend fun removeMovieFromFavorites(movieId: Int): Result<Unit> =
        movieDetailsLocalDataSource.removeMovieFromFavorites(movieId)
}