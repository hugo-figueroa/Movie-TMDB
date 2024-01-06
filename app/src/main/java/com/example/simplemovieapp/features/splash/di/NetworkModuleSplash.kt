package com.example.simplemovieapp.features.splash.di

import com.example.networking.MovieTMDBNetworking
import com.example.networking.constants.NetworkConstants
import com.example.simplemovieapp.BuildConfig
import com.example.simplemovieapp.features.splash.data.apiServices.SplashService
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

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
            .callAdapterFactory(CoroutineCallAdapterFactory())
            .buildRetrofitClient()
            .create(SplashService::class.java)
    }
}