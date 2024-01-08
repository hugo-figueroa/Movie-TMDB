package com.example.simplemovieapp.features.movieDetails.di

import com.example.networking.MovieTMDBNetworking
import com.example.networking.constants.NetworkConstants
import com.example.simplemovieapp.BuildConfig
import com.example.simplemovieapp.features.movieDetails.data.apiService.MovieDetailsService
import com.example.simplemovieapp.interceptors.ErrorsCoroutinesCallAdapterFactory
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * NetworkModuleMovieDetails
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@InstallIn(ViewModelComponent::class)
@Module
object NetworkModuleMovieDetails {
    @Provides
    fun provideMovieDetailsService(
        gson: Gson
    ): MovieDetailsService {
        return MovieTMDBNetworking
            .Builder(NetworkConstants.BASE_URL)
            .gson(gson)
            .enableDebugLogs(BuildConfig.DEBUG)
            .callAdapterFactory(ErrorsCoroutinesCallAdapterFactory())
            .buildRetrofitClient()
            .create(MovieDetailsService::class.java)
    }
}