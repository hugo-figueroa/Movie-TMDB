package com.example.simplemovieapp.features.movieLists.data.local.dataSources.implementations

import com.example.core.persistence.SharedPreferencesManager
import com.example.simplemovieapp.constants.Constants
import com.example.simplemovieapp.features.movieLists.data.local.dataSources.interfaces.MovieListLocalDataSource
import javax.inject.Inject

/**
 * MovieListLocalDataSourceImpl
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class MovieListLocalDataSourceImpl @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) : MovieListLocalDataSource {
    override suspend fun getBaseImageUrl(): String =
        sharedPreferencesManager.getString(Constants.BASE_IMAGE_URL_KEY, "")
}