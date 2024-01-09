package com.example.simplemovieapp.features.movieLists.domain.models

/**
 * PopularMoviesDomain
 *
 * @author (c) 2024, Hugo Figueroa
 * */
data class ListMoviesDomain(
    val page: Int,
    val results: List<MovieDomain>,
    val totalPages: Int,
    val totalResults: Int
)
