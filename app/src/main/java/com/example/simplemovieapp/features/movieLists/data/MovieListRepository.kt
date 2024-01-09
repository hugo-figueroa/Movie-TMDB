package com.example.simplemovieapp.features.movieLists.data

import com.example.core.models.Result
import com.example.simplemovieapp.features.movieLists.data.local.dataSources.interfaces.MovieListLocalDataSource
import com.example.simplemovieapp.features.movieLists.data.remote.dataSources.interfaces.MovieListRemoteDataSource
import com.example.simplemovieapp.features.movieLists.domain.models.ListMoviesDomain
import javax.inject.Inject

/**
 * MovieListRepository
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class MovieListRepository @Inject constructor(
    private val movieListRemoteDataSource: MovieListRemoteDataSource,
    private val movieListLocalDataSource: MovieListLocalDataSource
) {
    suspend fun getPopularMovies(page: Int): Result<ListMoviesDomain> =
        movieListRemoteDataSource.getPopularMovies(page)

    suspend fun getNowPlayingMovies(page: Int): Result<ListMoviesDomain> =
        movieListRemoteDataSource.getNowPlayingMovies(page)

    suspend fun getBaseImageUrl(): String = movieListLocalDataSource.getBaseImageUrl()
}