package com.example.simplemovieapp.features.splash.data.responseDto

import com.google.gson.annotations.SerializedName

/**
 * ImagesDTO
 *
 * @author (c) 2024, Hugo Figueroa
 * */
data class ImagesDto(
    @SerializedName("base_url")
    val baseImagesUrl: String
)