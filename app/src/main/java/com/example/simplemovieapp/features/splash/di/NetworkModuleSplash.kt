package com.example.simplemovieapp.features.splash.di

import com.example.networking.MovieTMDBNetworking
import com.example.networking.constants.NetworkConstants
import com.example.simplemovieapp.BuildConfig
import com.example.simplemovieapp.features.splash.data.remote.apiServices.SplashService
import com.example.simplemovieapp.interceptors.ErrorsCoroutinesCallAdapterFactory
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * NetworkModuleSplash
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@InstallIn(ViewModelComponent::class)
@Module
object NetworkModuleSplash {
    @Provides
    fun provideSplashService(
        gson: Gson
    ): SplashService {
        return MovieTMDBNetworking
            .Builder(NetworkConstants.BASE_URL)
            .gson(gson)
            .enableDebugLogs(BuildConfig.DEBUG)
            .callAdapterFactory(ErrorsCoroutinesCallAdapterFactory())
            .buildRetrofitClient()
            .create(SplashService::class.java)
    }
}