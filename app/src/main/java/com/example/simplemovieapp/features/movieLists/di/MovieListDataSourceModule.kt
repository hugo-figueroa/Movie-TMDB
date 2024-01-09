package com.example.simplemovieapp.features.movieLists.di

import com.example.simplemovieapp.features.movieLists.data.local.dataSources.implementations.MovieListLocalDataSourceImpl
import com.example.simplemovieapp.features.movieLists.data.local.dataSources.interfaces.MovieListLocalDataSource
import com.example.simplemovieapp.features.movieLists.data.remote.dataSources.implementations.MovieListRemoteDataSourceImpl
import com.example.simplemovieapp.features.movieLists.data.remote.dataSources.interfaces.MovieListRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * MovieListDataSourceModule
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieListDataSourceModule {

    @Binds
    abstract fun provideMovieListRemoteDataSource(
        movieListRemoteDataSourceImpl: MovieListRemoteDataSourceImpl
    ): MovieListRemoteDataSource

    @Binds
    abstract fun provideMovieListLocalDataSource(
        movieListLocalDataSourceImpl: MovieListLocalDataSourceImpl
    ): MovieListLocalDataSource
}