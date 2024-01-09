package com.example.simplemovieapp.features.movieDetails.data.apiService

import com.example.simplemovieapp.constants.Constants
import com.example.simplemovieapp.features.movieDetails.data.responseDto.MovieDetailsDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * MovieDetailsService
 *
 * @author (c) 2024, Hugo Figueroa
 * */
interface MovieDetailsService {

    @GET("movie/{movieId}")
    fun getMovieDetailsById(
        @Path(Constants.MOVIE_ID) movieId: Int,
        @Query(Constants.LANGUAGE_KEY) language: String = Constants.LANGUAGE,
        @Query(Constants.API_KEY) apiKey: String
    ): Deferred<MovieDetailsDto>
}