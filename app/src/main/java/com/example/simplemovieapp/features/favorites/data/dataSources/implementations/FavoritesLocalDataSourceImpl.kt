package com.example.simplemovieapp.features.favorites.data.dataSources.implementations

import android.database.sqlite.SQLiteException
import com.example.core.models.Result
import com.example.simplemovieapp.extensionFunction.getErrorResponse
import com.example.simplemovieapp.features.favorites.data.dataSources.implementations.mappers.GetFavoritesMapper
import com.example.simplemovieapp.features.favorites.data.dataSources.interfaces.FavoritesLocalDataSource
import com.example.simplemovieapp.features.favorites.db.FavoritesMoviesDao
import com.example.simplemovieapp.features.favorites.domain.models.MovieDetailsDomain
import com.example.simplemovieapp.features.favorites.exceptions.DataBaseException
import javax.inject.Inject

/**
 * FavoritesLocalDataSourceImpl
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class FavoritesLocalDataSourceImpl @Inject constructor(
    private val favoritesMoviesDao: FavoritesMoviesDao,
    private val getFavoritesMapper: GetFavoritesMapper
) : FavoritesLocalDataSource {
    override suspend fun getFavoritesMovies(): Result<List<MovieDetailsDomain>> {
        return try {
            val favoritesResult = favoritesMoviesDao.getFavorites()
            if (favoritesResult != null) {
                Result.Success(favoritesResult.map { getFavoritesMapper.map(it) })
            } else {
                Result.Error(DataBaseException())
            }
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
}