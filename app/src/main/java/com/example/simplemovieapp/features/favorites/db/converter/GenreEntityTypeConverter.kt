package com.example.simplemovieapp.features.favorites.db.converter

import androidx.room.TypeConverter
import com.example.simplemovieapp.features.favorites.data.entities.GenreEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Collections

/**
 * GenreEntityTypeConverter
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class GenreEntityTypeConverter {

    @TypeConverter
    fun stringToList(data: String?): List<GenreEntity> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<GenreEntity>>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<GenreEntity>?): String? {
        return if (someObjects != null) Gson().toJson(someObjects) else null
    }
}