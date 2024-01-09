package com.example.simplemovieapp.features.movieLists.data.responseDto

import com.google.gson.annotations.SerializedName

/**
 * MovieDto
 *
 * @author (c) 2024, Hugo Figueroa
 * */
data class MovieDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val language: String,
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
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)
