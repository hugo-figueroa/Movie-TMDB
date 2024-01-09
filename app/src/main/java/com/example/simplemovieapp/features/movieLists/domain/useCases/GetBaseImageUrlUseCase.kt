package com.example.simplemovieapp.features.movieLists.domain.useCases

import com.example.simplemovieapp.features.movieLists.data.MovieListRepository
import javax.inject.Inject

/**
 * GetBaseImageUrlUseCase
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class GetBaseImageUrlUseCase @Inject constructor(
    private val movieListRepository: MovieListRepository
) {
    suspend operator fun invoke(): String = movieListRepository.getBaseImageUrl()
}