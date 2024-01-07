package com.example.simplemovieapp.features.movieLists.domain.models

/**
 * MovieDomain
 *
 * @author (c) 2024, Hugo Figueroa
 * */
data class MovieDomain(
    val id: Int,
    val language: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: String
)
