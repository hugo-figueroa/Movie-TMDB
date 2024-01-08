package com.example.simplemovieapp.features.splash.data

import com.example.core.models.Result
import com.example.simplemovieapp.features.splash.data.local.dataSources.interfaces.SplashLocalDataSource
import com.example.simplemovieapp.features.splash.data.remote.dataSources.interfaces.SplashRemoteDataSource
import com.example.simplemovieapp.features.splash.domain.models.ConfigurationDomain
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * SplashRepository
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class SplashRepository @Inject constructor(
    private val splashRemoteDataSource: SplashRemoteDataSource,
    private val splashLocalDataSource: SplashLocalDataSource
) {
    suspend fun getConfiguration(): Result<Boolean> {
        return when (val resultCall =
            withContext(Dispatchers.IO) { splashRemoteDataSource.getConfiguration() }) {
            is Result.Success<ConfigurationDomain> -> {
                splashLocalDataSource.saveBaseImageUrl(resultCall.data.images.baseImagesUrl)
            }

            is Result.Error -> {
                resultCall
            }
        }
    }
}