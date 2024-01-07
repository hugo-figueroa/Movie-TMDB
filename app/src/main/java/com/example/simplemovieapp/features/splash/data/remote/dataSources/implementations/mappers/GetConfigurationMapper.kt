package com.example.simplemovieapp.features.splash.data.remote.dataSources.implementations.mappers

import com.example.core.utils.Mapper
import com.example.simplemovieapp.features.splash.data.remote.responseDto.ConfigurationDto
import com.example.simplemovieapp.features.splash.domain.models.ConfigurationDomain
import com.example.simplemovieapp.features.splash.domain.models.ImagesDomain
import javax.inject.Inject

/**
 * GetConfigurationMapper
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class GetConfigurationMapper @Inject constructor() : Mapper<ConfigurationDto, ConfigurationDomain> {
    override fun map(input: ConfigurationDto): ConfigurationDomain {
        return ConfigurationDomain(
            ImagesDomain(
                input.images.baseImagesUrl
            )
        )
    }
}