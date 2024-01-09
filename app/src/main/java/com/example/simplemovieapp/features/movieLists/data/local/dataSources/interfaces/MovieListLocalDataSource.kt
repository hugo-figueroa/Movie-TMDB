package com.example.simplemovieapp.features.movieLists.data.local.dataSources.interfaces

/**
 * MovieListLocalDataSource
 *
 * @author (c) 2024, Hugo Figueroa
 * */
interface MovieListLocalDataSource {
    suspend fun getBaseImageUrl(): String
}