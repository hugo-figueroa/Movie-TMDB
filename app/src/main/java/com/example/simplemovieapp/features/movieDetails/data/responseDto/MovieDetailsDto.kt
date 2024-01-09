package com.example.simplemovieapp.features.movieDetails.data.responseDto

import com.google.gson.annotations.SerializedName

/**
 * MovieDetailsDto
 *
 * @author (c) 2024, Hugo Figueroa
 * */
data class MovieDetailsDto(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("genres")
    val genres: List<GenreDto>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("revenue")
    val revenue: Int,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<LanguagesDto>,
    @SerializedName("status")
    val status: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)