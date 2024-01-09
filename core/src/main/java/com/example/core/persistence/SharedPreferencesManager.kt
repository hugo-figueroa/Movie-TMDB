package com.example.core.persistence

import android.app.Application
import android.content.Context
import javax.inject.Inject

/**
 * SharedPreferencesManager
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class SharedPreferencesManager @Inject constructor(private val application: Application) {
    companion object {
        const val MOVIES_PREFERENCES_STORE_NAME = "settings_data"
    }

    fun getString(key: String, default: String): String {
        val preferences =
            application.getSharedPreferences(MOVIES_PREFERENCES_STORE_NAME, Context.MODE_PRIVATE)
        if (preferences != null) {
            val result = preferences.getString(key, default)
            if (result != null) {
                return result
            }
        }
        return default
    }

    fun putString(key: String, value: String?) {
        val preferences =
            application.getSharedPreferences(MOVIES_PREFERENCES_STORE_NAME, Context.MODE_PRIVATE)
        if (preferences != null) {
            val editor = preferences.edit()
            editor.putString(key, value).commit()
        }
    }
}