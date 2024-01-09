package com.example.simplemovieapp.features.favorites.di

import com.example.simplemovieapp.features.favorites.data.dataSources.implementations.FavoritesLocalDataSourceImpl
import com.example.simplemovieapp.features.favorites.data.dataSources.interfaces.FavoritesLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * FavoritesDataSourceModule
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@Module
@InstallIn(ViewModelComponent::class)
abstract class FavoritesDataSourceModule {
    @Binds
    abstract fun provideFavoritesLocalDataSource(
        favoritesLocalDataSourceImpl: FavoritesLocalDataSourceImpl
    ): FavoritesLocalDataSource
}