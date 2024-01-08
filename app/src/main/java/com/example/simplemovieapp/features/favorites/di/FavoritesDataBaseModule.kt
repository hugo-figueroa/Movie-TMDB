package com.example.simplemovieapp.features.favorites.di

import android.app.Application
import androidx.room.Room
import com.example.simplemovieapp.features.favorites.db.FavoritesDataBase
import com.example.simplemovieapp.features.favorites.db.FavoritesMoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * FavoritesDataBaseModule
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@InstallIn(ViewModelComponent::class)
@Module
class FavoritesDataBaseModule {

    companion object {
        const val DB_NAME = "FavoritesMovies"
    }

    @Provides
    fun provideFavoritesDataBase(application: Application): FavoritesDataBase {
        return Room.databaseBuilder(
            application.applicationContext,
            FavoritesDataBase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    fun provideFavoritesMovieDao(favoritesDataBase: FavoritesDataBase): FavoritesMoviesDao {
        return favoritesDataBase.favoritesMoviesDao()
    }
}