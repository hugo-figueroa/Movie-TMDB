package com.example.simplemovieapp.features.movieDetails.data.dataSources.interfaces

import com.example.core.models.Result
import com.example.simplemovieapp.features.movieDetails.domain.models.MovieDetailsDomain

/**
 * MovieDetailsRemoteDataSource
 *
 * @author (c) 2024, Hugo Figueroa
 * */
interface MovieDetailsRemoteDataSource {
    suspend fun getMovieDetailsById(movieId: Int): Result<MovieDetailsDomain>
}