package com.example.simplemovieapp.features.favorites.db.converter

import androidx.room.TypeConverter
import com.example.simplemovieapp.features.favorites.data.entities.LanguageEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Collections

/**
 * LanguageEntityTypeConverter
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class LanguageEntityTypeConverter {

    @TypeConverter
    fun stringToList(data: String?): List<LanguageEntity> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<LanguageEntity>>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<LanguageEntity>?): String? {
        return if (someObjects != null) Gson().toJson(someObjects) else null
    }
}