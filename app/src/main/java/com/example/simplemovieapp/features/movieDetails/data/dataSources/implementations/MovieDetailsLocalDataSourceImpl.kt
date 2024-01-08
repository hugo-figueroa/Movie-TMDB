package com.example.simplemovieapp.features.movieDetails.data.dataSources.implementations

import android.database.sqlite.SQLiteException
import com.example.core.models.Result
import com.example.simplemovieapp.extensionFunction.getErrorResponse
import com.example.simplemovieapp.features.favorites.db.FavoritesMoviesDao
import com.example.simplemovieapp.features.movieDetails.data.dataSources.implementations.mappers.SaveMovieToFavoritesMapper
import com.example.simplemovieapp.features.movieDetails.data.dataSources.interfaces.MovieDetailsLocalDataSource
import com.example.simplemovieapp.features.movieDetails.domain.models.MovieDetailsDomain
import javax.inject.Inject

/**
 * MovieDetailsLocalDataSourceImpl
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class MovieDetailsLocalDataSourceImpl @Inject constructor(
    private val favoritesMoviesDao: FavoritesMoviesDao,
    private val saveMovieToFavoritesMapper: SaveMovieToFavoritesMapper
) : MovieDetailsLocalDataSource {
    override suspend fun saveMovieToFavorites(movie: MovieDetailsDomain): Result<Unit> {
        return try {
            favoritesMoviesDao.insertMovieToFavorites(saveMovieToFavoritesMapper.map(movie))
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(e)
        }
    }

    override suspend fun removeMovieFromFavorites(movieId: Int): Result<Unit> {
        return try {
            favoritesMoviesDao.removeMovieFromFavorites(movieId)
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(e)
        }
    }

    override suspend fun isMovieOnFavorites(movieId: Int): Result<Boolean> {
        return try {
            val favoritesResult = favoritesMoviesDao.getFavoriteById(movieId)
            if (favoritesResult != null) {
                Result.Success(true)
            } else {
                Result.Success(false)
            }
        } catch (e: SQLiteException) {
            Result.Error(e)
        }
    }
}