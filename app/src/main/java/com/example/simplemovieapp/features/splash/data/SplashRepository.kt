package com.example.simplemovieapp.features.splash.data

import com.example.core.models.Result
import com.example.simplemovieapp.features.splash.data.dataSources.interfaces.SplashRemoteDataSource
import com.example.simplemovieapp.features.splash.domain.models.ConfigurationDomain
import javax.inject.Inject

/**
 * SplashRepository
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class SplashRepository @Inject constructor(
    private val splashRemoteDataSource: SplashRemoteDataSource
) {
    suspend fun getConfiguration(): Result<ConfigurationDomain> =
        splashRemoteDataSource.getConfiguration()
}