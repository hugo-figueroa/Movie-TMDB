package com.example.simplemovieapp.features.movieLists.data.remote.dataSources.implementations

import com.example.core.models.Result
import com.example.simplemovieapp.BuildConfig
import com.example.simplemovieapp.features.movieLists.data.remote.apiServices.MovieListService
import com.example.simplemovieapp.features.movieLists.data.remote.dataSources.implementations.mappers.GetListMoviesMapper
import com.example.simplemovieapp.features.movieLists.data.remote.dataSources.interfaces.MovieListRemoteDataSource
import com.example.simplemovieapp.features.movieLists.domain.models.ListMoviesDomain
import java.lang.Exception
import javax.inject.Inject

/**
 * MovieListRemoteDataSourceImpl
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class MovieListRemoteDataSourceImpl @Inject constructor(
    private val movieListService: MovieListService,
    private val getListMoviesMapper: GetListMoviesMapper
) : MovieListRemoteDataSource {
    override suspend fun getPopularMovies(page: Int): Result<ListMoviesDomain> {
        return try {
            val response =
                movieListService.getPopularMovies(page = page, apiKey = BuildConfig.TMDB_KEY)
                    .await()
            Result.Success(getListMoviesMapper.map(response))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getNowPlayingMovies(page: Int): Result<ListMoviesDomain> {
        return try {
            val response =
                movieListService.getNowPlayingMovies(page = page, apiKey = BuildConfig.TMDB_KEY)
                    .await()
            Result.Success(getListMoviesMapper.map(response))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}