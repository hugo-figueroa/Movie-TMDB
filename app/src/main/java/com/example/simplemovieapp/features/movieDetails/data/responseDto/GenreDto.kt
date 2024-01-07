package com.example.simplemovieapp.features.movieDetails.data.responseDto

import com.google.gson.annotations.SerializedName

/**
 * GenreDto
 *
 * @author (c) 2024, Hugo Figueroa
 * */
data class GenreDto(
    @SerializedName("name")
    val name: String
)
