package com.example.simplemovieapp.features.movieLists.data.dataSources.implementations

import com.example.core.models.Result
import com.example.simplemovieapp.BuildConfig
import com.example.simplemovieapp.extensionFunction.getErrorResponse
import com.example.simplemovieapp.features.movieLists.data.apiServices.HomeService
import com.example.simplemovieapp.features.movieLists.data.dataSources.implementations.mappers.GetPopularMoviesMapper
import com.example.simplemovieapp.features.movieLists.data.dataSources.interfaces.HomeRemoteDataSource
import com.example.simplemovieapp.features.movieLists.domain.models.PopularMoviesDomain
import java.lang.Exception
import javax.inject.Inject

/**
 * HomeRemoteDataSourceImpl
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class HomeRemoteDataSourceImpl @Inject constructor(
    private val homeService: HomeService,
    private val getPopularMoviesMapper: GetPopularMoviesMapper
) : HomeRemoteDataSource {
    override suspend fun getPopularMovies(page: Int): Result<PopularMoviesDomain> {
        return try {
            val response =
                homeService.getPopularMoviesAsync(page = page, apiKey = BuildConfig.TMDB_KEY)
                    .await()
            Result.Success(getPopularMoviesMapper.map(response))
        } catch (e: Exception) {
            Result.Error(e.getErrorResponse())
        }
    }

}