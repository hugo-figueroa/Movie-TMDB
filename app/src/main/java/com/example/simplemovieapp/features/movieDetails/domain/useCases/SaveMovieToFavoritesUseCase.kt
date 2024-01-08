package com.example.simplemovieapp.features.movieDetails.domain.useCases

import com.example.core.models.Result
import com.example.simplemovieapp.features.movieDetails.data.MovieDetailsRepository
import com.example.simplemovieapp.features.movieDetails.domain.models.MovieDetailsDomain
import javax.inject.Inject

/**
 * SaveMovieToFavorites
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class SaveMovieToFavoritesUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) {
    suspend operator fun invoke(movie: MovieDetailsDomain): Result<Unit> =
        movieDetailsRepository.saveMovieToFavorites(movie)
}