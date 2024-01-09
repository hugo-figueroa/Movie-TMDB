package com.example.simplemovieapp.features.movieLists.data.remote.responseDto

import com.google.gson.annotations.SerializedName

/**
 * PopularMoviesDto
 *
 * @author (c) 2024, Hugo Figueroa
 * */
data class ListMoviesDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
