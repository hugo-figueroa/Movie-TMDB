package com.example.core.utils

/**
 * Mapper
 *
 * @author (c) 2024, Hugo Figueroa
 * */
interface Mapper<I, O> {
    fun map(input: I): O
}
