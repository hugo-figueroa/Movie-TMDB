package com.example.simplemovieapp.features.movieLists.di

import com.example.networking.MovieTMDBNetworking
import com.example.networking.constants.NetworkConstants
import com.example.simplemovieapp.BuildConfig
import com.example.simplemovieapp.features.movieLists.data.remote.apiServices.MovieListService
import com.example.simplemovieapp.interceptors.ErrorsCoroutinesCallAdapterFactory
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * NetworkModuleMovieList
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@InstallIn(ViewModelComponent::class)
@Module
object NetworkModuleMovieList {
    @Provides
    fun provideMovieListService(
        gson: Gson
    ): MovieListService {
        return MovieTMDBNetworking
            .Builder(NetworkConstants.BASE_URL)
            .gson(gson)
            .enableDebugLogs(BuildConfig.DEBUG)
            .callAdapterFactory(ErrorsCoroutinesCallAdapterFactory())
            .buildRetrofitClient()
            .create(MovieListService::class.java)
    }
}