package com.example.simplemovieapp.extensionFunction

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException

/**
 * ExceptionEF
 *
 * @author (c) 2024, Hugo Figueroa
 * */
fun Exception.getErrorResponse(): String {
    val gson = Gson()
    val type = object : TypeToken<String>() {}.type
    var errorResponse = ""
    when (this) {
        is HttpException -> {
            this.response()?.let { response ->
                response.errorBody()?.let {
                    errorResponse = gson.fromJson(it.charStream(), type)
                }
            }
        }
    }
    return errorResponse
}