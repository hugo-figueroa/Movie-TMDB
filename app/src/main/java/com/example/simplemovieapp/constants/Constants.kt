package com.example.simplemovieapp.constants

object Constants {
    // Keys
    const val BASE_IMAGE_URL_KEY = "BASE_IMAGE_URL_KEY"

    const val API_KEY = "api_key"
    const val INCLUDE_ADULT_KEY = "include_adult"
    const val INCLUDE_VIDEO_KEY = "include_video"
    const val LANGUAGE_KEY = "language"
    const val PAGE_KEY = "page"
    const val SHORT_BY_KEY = "sort_by"
    const val WITH_RELEASE_TYPE_KEY = "with_release_type"
    const val MOVIE_ID = "movieId"

    // Values
    const val INCLUDE_ADULT = false
    const val INCLUDE_VIDEO = false
    const val LANGUAGE = "en-US"
    const val SHORT_BY = "popularity.desc"
    const val WITH_RELEASE_TYPE = "2|3"

    const val GENERIC_DATA_BASE_ERROR = "Error when get information from database"
}