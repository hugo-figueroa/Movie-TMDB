package com.example.simplemovieapp.features.movieLists.data

import com.example.core.models.Result
import com.example.simplemovieapp.features.movieLists.data.dataSources.interfaces.HomeRemoteDataSource
import com.example.simplemovieapp.features.movieLists.domain.models.PopularMoviesDomain
import javax.inject.Inject

/**
 * HomeRepository
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class HomeRepository @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource
) {
    suspend fun getPopularMovies(page: Int): Result<PopularMoviesDomain> =
        homeRemoteDataSource.getPopularMovies(page)
}