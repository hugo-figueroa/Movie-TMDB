package com.example.simplemovieapp.features.movieLists.data.remote.dataSources.interfaces

import com.example.core.models.Result
import com.example.simplemovieapp.features.movieLists.domain.models.ListMoviesDomain

/**
 * MovieListRemoteDataSource
 *
 * @author (c) 2024, Hugo Figueroa
 * */
interface MovieListRemoteDataSource {
    suspend fun getPopularMovies(page: Int): Result<ListMoviesDomain>
    suspend fun getNowPlayingMovies(page: Int): Result<ListMoviesDomain>
}