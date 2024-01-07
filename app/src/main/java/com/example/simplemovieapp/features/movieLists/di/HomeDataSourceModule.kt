package com.example.simplemovieapp.features.movieLists.di

import com.example.simplemovieapp.features.movieLists.data.dataSources.implementations.HomeRemoteDataSourceImpl
import com.example.simplemovieapp.features.movieLists.data.dataSources.interfaces.HomeRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * HomeDataSourceModule
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeDataSourceModule {

    @Binds
    abstract fun provideHomeRemoteDataSource(
        homeRemoteDataSourceImpl: HomeRemoteDataSourceImpl
    ): HomeRemoteDataSource
}