package com.example.simplemovieapp.features.favorites.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.simplemovieapp.features.favorites.data.entities.MovieEntity

/**
 * FavoritesMoviesDao
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@Dao
interface FavoritesMoviesDao {
    @Insert(onConflict = REPLACE)
    fun insertMovieToFavorites(movieEntity: MovieEntity)

    @Query("SELECT * FROM ${MovieEntity.TABLE_NAME}")
    fun getFavorites(): List<MovieEntity>?

    @Query("SELECT * FROM ${MovieEntity.TABLE_NAME} WHERE ${MovieEntity.PRIMARY_KEY_NAME} = :id")
    fun getFavoriteById(id: Int): MovieEntity?

    @Query("DELETE FROM ${MovieEntity.TABLE_NAME} WHERE ${MovieEntity.PRIMARY_KEY_NAME} = :id")
    fun removeMovieFromFavorites(id: Int)
}