package com.example.simplemovieapp.features.splash.di

import com.example.simplemovieapp.features.splash.data.dataSources.implementations.SplashRemoteDataSourceImpl
import com.example.simplemovieapp.features.splash.data.dataSources.interfaces.SplashRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * SplashDataSourceModule
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@Module
@InstallIn(ViewModelComponent::class)
abstract class SplashDataSourceModule {
    @Binds
    abstract fun provideSplashRemoteDataSource(
        splashRemoteDataSourceImpl: SplashRemoteDataSourceImpl
    ): SplashRemoteDataSource
}