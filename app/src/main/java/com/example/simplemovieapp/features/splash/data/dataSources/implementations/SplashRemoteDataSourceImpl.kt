package com.example.simplemovieapp.features.splash.data.dataSources.implementations

import com.example.core.models.Result
import com.example.simplemovieapp.BuildConfig
import com.example.simplemovieapp.extensionFunction.getErrorResponse
import com.example.simplemovieapp.features.splash.data.apiServices.SplashService
import com.example.simplemovieapp.features.splash.data.dataSources.implementations.mappers.GetConfigurationMapper
import com.example.simplemovieapp.features.splash.data.dataSources.interfaces.SplashRemoteDataSource
import com.example.simplemovieapp.features.splash.domain.models.ConfigurationDomain
import java.lang.Exception
import javax.inject.Inject

/**
 * SplashRemoteDataSourceImpl
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class SplashRemoteDataSourceImpl @Inject constructor(
    private val splashService: SplashService,
    private val getConfigurationMapper: GetConfigurationMapper
) : SplashRemoteDataSource {
    override suspend fun getConfiguration(): Result<ConfigurationDomain> {
        return try {
            val response = splashService.getConfigurationAsync(BuildConfig.TMDB_KEY).await()
            Result.Success(getConfigurationMapper.map(response))
        } catch (e: Exception) {
            Result.Error(e.getErrorResponse())
        }
    }
}