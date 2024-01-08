package com.example.simplemovieapp.features.favorites.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.simplemovieapp.features.favorites.data.entities.MovieEntity.Companion.TABLE_NAME
import com.example.simplemovieapp.features.favorites.db.converter.GenreEntityTypeConverter
import com.example.simplemovieapp.features.favorites.db.converter.LanguageEntityTypeConverter

/**
 * MovieEntity
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@Entity(tableName = TABLE_NAME)
data class MovieEntity(
    @PrimaryKey
    var id: Int,
    var adult: Boolean? = null,
    @TypeConverters(GenreEntityTypeConverter::class)
    var genres: List<GenreEntity>? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var releaseDate: String? = null,
    var revenue: Int? = null,
    var runtime: Int? = null,
    @TypeConverters(LanguageEntityTypeConverter::class)
    var spokenLanguages: List<LanguageEntity>? = null,
    var status: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null
) {
    companion object {
        const val TABLE_NAME = "Movie"
        const val PRIMARY_KEY_NAME = "id"
    }

}