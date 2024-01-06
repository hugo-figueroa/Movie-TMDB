package com.example.simplemovieapp.features.splash.data.dataSources.interfaces

import com.example.core.models.Result
import com.example.simplemovieapp.features.splash.domain.models.ConfigurationDomain

/**
 * SplashRemoteDataSource
 *
 * @author (c) 2024, Hugo Figueroa
 * */
interface SplashRemoteDataSource {
    suspend fun getConfiguration(): Result<ConfigurationDomain>
}