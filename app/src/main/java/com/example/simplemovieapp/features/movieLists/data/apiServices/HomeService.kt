package com.example.simplemovieapp.features.movieLists.data.apiServices

import com.example.simplemovieapp.constants.Constants
import com.example.simplemovieapp.features.movieLists.data.responseDto.PopularMoviesDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * HomeService
 *
 * @author (c) 2024, Hugo Figueroa
 * */
interface HomeService {
    @GET("movie/popular")
    fun getPopularMoviesAsync(
        @Query(Constants.INCLUDE_ADULT_KEY) includeAdult: Boolean = Constants.INCLUDE_ADULT,
        @Query(Constants.INCLUDE_VIDEO_KEY) includeVideo: Boolean = Constants.INCLUDE_VIDEO,
        @Query(Constants.LANGUAGE_KEY) language: String = Constants.LANGUAGE,
        @Query(Constants.SHORT_BY_KEY) shortBy: String = Constants.SHORT_BY,
        @Query(Constants.PAGE_KEY) page: Int,
        @Query(Constants.API_KEY) apiKey: String
    ): Deferred<PopularMoviesDto>
}