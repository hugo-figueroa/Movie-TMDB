package com.example.simplemovieapp.features.splash.domain.useCases

import com.example.core.models.Result
import com.example.simplemovieapp.features.splash.data.SplashRepository
import com.example.simplemovieapp.features.splash.domain.models.ConfigurationDomain
import javax.inject.Inject

/**
 * GetConfigurationUseCase
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class GetConfigurationUseCase @Inject constructor(
    private val splashRepository: SplashRepository
) {
    suspend operator fun invoke(): Result<ConfigurationDomain> =
        splashRepository.getConfiguration()
}