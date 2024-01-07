package com.example.simplemovieapp.features.movieLists.domain.useCases

import com.example.core.models.Result
import com.example.simplemovieapp.features.movieLists.data.HomeRepository
import com.example.simplemovieapp.features.movieLists.domain.models.PopularMoviesDomain
import javax.inject.Inject

/**
 * GetPopularMoviesUseCase
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class GetPopularMoviesUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(page: Int): Result<PopularMoviesDomain> =
        homeRepository.getPopularMovies(page)
}