package com.example.simplemovieapp.features.movieDetails.di

import com.example.simplemovieapp.features.movieDetails.data.dataSources.implementations.MovieDetailsRemoteDataSourceImpl
import com.example.simplemovieapp.features.movieDetails.data.dataSources.interfaces.MovieDetailsRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * MovieDetailsDataSourceModule
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieDetailsDataSourceModule {
    @Binds
    abstract fun provideMovieDetailsRemoteDataSource(
        movieDetailsRemoteDataSourceImpl: MovieDetailsRemoteDataSourceImpl
    ): MovieDetailsRemoteDataSource
}