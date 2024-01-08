package com.example.simplemovieapp.features.favorites.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.simplemovieapp.features.favorites.data.entities.MovieEntity
import com.example.simplemovieapp.features.favorites.db.converter.GenreEntityTypeConverter
import com.example.simplemovieapp.features.favorites.db.converter.LanguageEntityTypeConverter

/**
 * FavoritesDataBase
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@Database(
    entities = [
        MovieEntity::class
    ],
    version = FavoritesDataBase.VERSION,
    exportSchema = true
)
@TypeConverters(
    GenreEntityTypeConverter::class,
    LanguageEntityTypeConverter::class
)
abstract class FavoritesDataBase : RoomDatabase() {

    abstract fun favoritesMoviesDao(): FavoritesMoviesDao

    companion object {
        const val VERSION = 1
    }
}