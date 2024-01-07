package com.example.simplemovieapp.features.splash.data.remote.apiServices

import com.example.simplemovieapp.constants.Constants
import com.example.simplemovieapp.features.splash.data.remote.responseDto.ConfigurationDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * SplashService
 *
 * @author (c) 2024, Hugo Figueroa
 * */
interface SplashService {
    @GET("configuration")
    fun getConfigurationAsync(@Query(Constants.API_KEY) apiKey: String): Deferred<ConfigurationDto>
}