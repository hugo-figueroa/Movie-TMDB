package com.example.simplemovieapp.features.movieDetails.domain.useCases

import com.example.core.models.Result
import com.example.simplemovieapp.features.movieDetails.data.MovieDetailsRepository
import javax.inject.Inject

/**
 * RemoveMovieFromFavoritesUseCase
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class RemoveMovieFromFavoritesUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) {
    suspend operator fun invoke(movieId: Int): Result<Unit> =
        movieDetailsRepository.removeMovieFromFavorites(movieId)
}