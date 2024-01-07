package com.example.simplemovieapp.features.movieLists.data.dataSources.implementations

import com.example.core.models.Result
import com.example.simplemovieapp.BuildConfig
import com.example.simplemovieapp.extensionFunction.getErrorResponse
import com.example.simplemovieapp.features.movieLists.data.apiServices.HomeService
import com.example.simplemovieapp.features.movieLists.data.dataSources.implementations.mappers.GetListMoviesMapper
import com.example.simplemovieapp.features.movieLists.data.dataSources.interfaces.HomeRemoteDataSource
import com.example.simplemovieapp.features.movieLists.domain.models.ListMoviesDomain
import java.lang.Exception
import javax.inject.Inject

/**
 * HomeRemoteDataSourceImpl
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class HomeRemoteDataSourceImpl @Inject constructor(
    private val homeService: HomeService,
    private val getListMoviesMapper: GetListMoviesMapper
) : HomeRemoteDataSource {
    override suspend fun getPopularMovies(page: Int): Result<ListMoviesDomain> {
        return try {
            val response =
                homeService.getPopularMovies(page = page, apiKey = BuildConfig.TMDB_KEY)
                    .await()
            Result.Success(getListMoviesMapper.map(response))
        } catch (e: Exception) {
            Result.Error(e.getErrorResponse())
        }
    }

    override suspend fun getNowPlayingMovies(page: Int): Result<ListMoviesDomain> {
        return try {
            val response =
                homeService.getNowPlayingMovies(page = page, apiKey = BuildConfig.TMDB_KEY)
                    .await()
            Result.Success(getListMoviesMapper.map(response))
        } catch (e: Exception) {
            Result.Error(e.getErrorResponse())
        }
    }
}