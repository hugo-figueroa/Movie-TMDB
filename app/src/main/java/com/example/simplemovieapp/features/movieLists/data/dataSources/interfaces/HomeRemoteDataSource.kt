package com.example.simplemovieapp.features.movieLists.data.dataSources.interfaces

import com.example.core.models.Result
import com.example.simplemovieapp.features.movieLists.domain.models.PopularMoviesDomain

/**
 * HomeRemoteDataSource
 *
 * @author (c) 2024, Hugo Figueroa
 * */
interface HomeRemoteDataSource {
    suspend fun getPopularMovies(page: Int): Result<PopularMoviesDomain>
}