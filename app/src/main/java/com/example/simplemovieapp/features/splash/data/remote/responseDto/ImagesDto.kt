package com.example.simplemovieapp.features.splash.data.remote.responseDto

import com.google.gson.annotations.SerializedName

/**
 * ImagesDTO
 *
 * @author (c) 2024, Hugo Figueroa
 * */
data class ImagesDto(
    @SerializedName("secure_base_url")
    val secureBaseUrl: String
)