package com.example.simplemovieapp.features.favorites.domain.useCases

import com.example.core.models.Result
import com.example.simplemovieapp.features.favorites.data.FavoritesRepository
import javax.inject.Inject

/**
 * RemoveMovieFromFavoritesUseCase
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class RemoveMovieFromFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(movieId: Int): Result<Unit> =
        favoritesRepository.removeMovieFromFavorites(movieId)
}