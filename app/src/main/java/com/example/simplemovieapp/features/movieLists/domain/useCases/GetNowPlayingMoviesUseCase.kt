package com.example.simplemovieapp.features.movieLists.domain.useCases

import com.example.core.models.Result
import com.example.simplemovieapp.features.movieLists.data.MovieListRepository
import com.example.simplemovieapp.features.movieLists.domain.models.ListMoviesDomain
import javax.inject.Inject

/**
 * GetNowPlayingMoviesUseCase
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class GetNowPlayingMoviesUseCase @Inject constructor(
    private val movieListRepository: MovieListRepository
) {
    suspend operator fun invoke(page: Int): Result<ListMoviesDomain> =
        movieListRepository.getNowPlayingMovies(page)
}