package com.example.simplemovieapp.features.splash.data.local.dataSources.interfaces

import com.example.core.models.Result

/**
 * SplashLocalDataSource
 *
 * @author (c) 2024, Hugo Figueroa
 * */
interface SplashLocalDataSource {
    suspend fun saveBaseImageUrl(url: String): Result<Boolean>
}