package com.example.simplemovieapp.features.movieLists.di

import com.example.networking.MovieTMDBNetworking
import com.example.networking.constants.NetworkConstants
import com.example.simplemovieapp.BuildConfig
import com.example.simplemovieapp.features.movieLists.data.apiServices.HomeService
import com.example.simplemovieapp.interceptors.ErrorsCoroutinesCallAdapterFactory
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * NetworkModuleHome
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@InstallIn(ViewModelComponent::class)
@Module
object NetworkModuleHome {
    @Provides
    fun provideHomeService(
        gson: Gson
    ): HomeService {
        return MovieTMDBNetworking
            .Builder(NetworkConstants.BASE_URL)
            .gson(gson)
            .enableDebugLogs(BuildConfig.DEBUG)
            .callAdapterFactory(ErrorsCoroutinesCallAdapterFactory())
            .buildRetrofitClient()
            .create(HomeService::class.java)
    }
}