package com.example.simplemovieapp.features.movieDetails.domain.useCases

import com.example.core.models.Result
import com.example.simplemovieapp.features.movieDetails.data.MovieDetailsRepository
import javax.inject.Inject

/**
 * IsMovieOnFavoritesUseCase
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class IsMovieOnFavoritesUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) {
    suspend operator fun invoke(movieId: Int): Result<Boolean> =
        movieDetailsRepository.isMovieOnFavorites(movieId)
}