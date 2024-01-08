package com.example.core.models

/**
 * Result
 *
 * @author (c) 2024, Hugo Figueroa
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val throwable: Throwable) : Result<Nothing>()
}
