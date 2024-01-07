package com.example.simplemovieapp.features.movieDetails.data.dataSources.implementations

import com.example.core.models.Result
import com.example.simplemovieapp.BuildConfig
import com.example.simplemovieapp.extensionFunction.getErrorResponse
import com.example.simplemovieapp.features.movieDetails.data.apiService.MovieDetailsService
import com.example.simplemovieapp.features.movieDetails.data.dataSources.implementations.mappers.GetMovieDetailsMapper
import com.example.simplemovieapp.features.movieDetails.data.dataSources.interfaces.MovieDetailsRemoteDataSource
import com.example.simplemovieapp.features.movieDetails.domain.models.MovieDetailsDomain
import java.lang.Exception
import javax.inject.Inject

/**
 * MovieDetailsRemoteDataSourceImpl
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class MovieDetailsRemoteDataSourceImpl @Inject constructor(
    private val movieDetailsService: MovieDetailsService,
    private val getMovieDetailsMapper: GetMovieDetailsMapper
) : MovieDetailsRemoteDataSource {
    override suspend fun getMovieDetailsById(movieId: Int): Result<MovieDetailsDomain> {
        return try {
            val response =
                movieDetailsService.getMovieDetailsById(movieId = movieId, apiKey = BuildConfig.TMDB_KEY)
                    .await()
            Result.Success(getMovieDetailsMapper.map(response))
        } catch (e: Exception) {
            Result.Error(e.getErrorResponse())
        }
    }

}