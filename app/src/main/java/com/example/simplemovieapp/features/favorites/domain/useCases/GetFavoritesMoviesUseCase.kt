package com.example.simplemovieapp.features.favorites.domain.useCases

import com.example.core.models.Result
import com.example.simplemovieapp.features.favorites.data.FavoritesRepository
import com.example.simplemovieapp.features.favorites.domain.models.MovieDetailsDomain
import javax.inject.Inject

/**
 * GetFavoritesMoviesUseCase
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class GetFavoritesMoviesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(): Result<List<MovieDetailsDomain>> =
        favoritesRepository.getFavoritesMovies()
}