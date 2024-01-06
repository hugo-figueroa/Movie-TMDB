package com.example.simplemovieapp.features.splash.data.responseDto

import com.google.gson.annotations.SerializedName

/**
 * ConfigurationDTO
 *
 * @author (c) 2024, Hugo Figueroa
 * */
data class ConfigurationDto(
    @SerializedName("images")
    val images: ImagesDto
)