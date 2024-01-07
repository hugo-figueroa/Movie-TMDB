package com.example.simplemovieapp.features.movieDetails.data

import com.example.core.models.Result
import com.example.simplemovieapp.features.movieDetails.data.dataSources.interfaces.MovieDetailsRemoteDataSource
import com.example.simplemovieapp.features.movieDetails.domain.models.MovieDetailsDomain
import javax.inject.Inject

/**
 * MovieDetailsRepository
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class MovieDetailsRepository @Inject constructor(
    private val movieDetailsRemoteDataSource: MovieDetailsRemoteDataSource
) {
    suspend fun getMovieDetailsById(movieId: Int): Result<MovieDetailsDomain> =
        movieDetailsRemoteDataSource.getMovieDetailsById(movieId)
}