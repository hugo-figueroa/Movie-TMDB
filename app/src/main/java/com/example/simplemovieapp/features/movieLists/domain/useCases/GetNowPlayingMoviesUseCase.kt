package com.example.simplemovieapp.features.movieLists.domain.useCases

import com.example.core.models.Result
import com.example.simplemovieapp.features.movieLists.data.HomeRepository
import com.example.simplemovieapp.features.movieLists.domain.models.ListMoviesDomain
import javax.inject.Inject

/**
 * GetNowPlayingMoviesUseCase
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class GetNowPlayingMoviesUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(page: Int): Result<ListMoviesDomain> =
        homeRepository.getNowPlayingMovies(page)
}