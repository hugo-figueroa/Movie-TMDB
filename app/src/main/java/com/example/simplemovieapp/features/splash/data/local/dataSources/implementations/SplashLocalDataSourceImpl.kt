package com.example.simplemovieapp.features.splash.data.local.dataSources.implementations

import com.example.core.models.Result
import com.example.core.persistence.SharedPreferencesManager
import com.example.simplemovieapp.constants.Constants
import com.example.simplemovieapp.features.splash.data.local.dataSources.interfaces.SplashLocalDataSource
import java.lang.Exception
import javax.inject.Inject

/**
 * SplashLocalDataSourceImpl
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class SplashLocalDataSourceImpl @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) : SplashLocalDataSource {
    override suspend fun saveBaseImageUrl(url: String): Result<Boolean> {
        return try {
            sharedPreferencesManager.putString(Constants.BASE_IMAGE_URL_KEY, url)
            Result.Success(true)
        } catch (e: Exception) {
            Result.Success(false)
        }
    }
}