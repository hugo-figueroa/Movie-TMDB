package com.example.simplemovieapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * MovieTMDBApplication
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@HiltAndroidApp
class MovieTMDBApplication: Application() {

    companion object {
        lateinit var instance: MovieTMDBApplication
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}