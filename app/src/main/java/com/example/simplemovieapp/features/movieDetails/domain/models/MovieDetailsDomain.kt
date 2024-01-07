package com.example.simplemovieapp.features.movieDetails.domain.models

/**
 * MovieDetailsDomain
 *
 * @author (c) 2024, Hugo Figueroa
 * */
data class MovieDetailsDomain(
    val adult: Boolean,
    val genres: List<GenreDomain>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<LanguagesDomain>,
    val status: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
