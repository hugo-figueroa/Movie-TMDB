package com.example.simplemovieapp.features.movieLists.data.remote.apiServices

import com.example.simplemovieapp.constants.Constants
import com.example.simplemovieapp.features.movieLists.data.remote.responseDto.ListMoviesDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * MovieListService
 *
 * @author (c) 2024, Hugo Figueroa
 * */
interface MovieListService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query(Constants.INCLUDE_ADULT_KEY) includeAdult: Boolean = Constants.INCLUDE_ADULT,
        @Query(Constants.INCLUDE_VIDEO_KEY) includeVideo: Boolean = Constants.INCLUDE_VIDEO,
        @Query(Constants.LANGUAGE_KEY) language: String = Constants.LANGUAGE,
        @Query(Constants.SHORT_BY_KEY) shortBy: String = Constants.SHORT_BY,
        @Query(Constants.PAGE_KEY) page: Int,
        @Query(Constants.API_KEY) apiKey: String
    ): Deferred<ListMoviesDto>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query(Constants.INCLUDE_ADULT_KEY) includeAdult: Boolean = Constants.INCLUDE_ADULT,
        @Query(Constants.INCLUDE_VIDEO_KEY) includeVideo: Boolean = Constants.INCLUDE_VIDEO,
        @Query(Constants.LANGUAGE_KEY) language: String = Constants.LANGUAGE,
        @Query(Constants.SHORT_BY_KEY) shortBy: String = Constants.SHORT_BY,
        @Query(Constants.WITH_RELEASE_TYPE_KEY) withReleaseTypeKey: String = Constants.WITH_RELEASE_TYPE,
        @Query(Constants.PAGE_KEY) page: Int,
        @Query(Constants.API_KEY) apiKey: String
    ): Deferred<ListMoviesDto>
}