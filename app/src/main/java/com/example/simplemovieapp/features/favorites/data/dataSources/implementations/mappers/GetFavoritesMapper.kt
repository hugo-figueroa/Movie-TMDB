package com.example.simplemovieapp.features.favorites.data.dataSources.implementations.mappers

import com.example.core.utils.Mapper
import com.example.simplemovieapp.features.favorites.data.entities.MovieEntity
import com.example.simplemovieapp.features.favorites.domain.models.GenreDomain
import com.example.simplemovieapp.features.favorites.domain.models.LanguagesDomain
import com.example.simplemovieapp.features.favorites.domain.models.MovieDetailsDomain
import javax.inject.Inject

/**
 * GetFavoritesMapper
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class GetFavoritesMapper @Inject constructor() : Mapper<MovieEntity, MovieDetailsDomain> {
    override fun map(input: MovieEntity): MovieDetailsDomain {
        return MovieDetailsDomain(
            adult = input.adult ?: false,
            genres = input.genres?.map { genreDomain ->
                GenreDomain(
                    name = genreDomain.name ?: ""
                )
            } ?: listOf(),
            id = input.id,
            originalLanguage = input.originalLanguage ?: "",
            originalTitle = input.originalTitle ?: "",
            overview = input.overview ?: "",
            popularity = input.popularity ?: 0.0,
            posterPath = input.posterPath ?: "",
            releaseDate = input.releaseDate ?: "",
            revenue = input.revenue ?: 0,
            runtime = input.runtime ?: 0,
            spokenLanguages = input.spokenLanguages?.map { languageDomain ->
                LanguagesDomain(
                    name = languageDomain.name ?: ""
                )
            } ?: listOf(),
            status = input.status ?: "",
            title = input.title ?: "",
            video = input.video ?: false,
            voteAverage = input.voteAverage ?: 0.0,
            voteCount = input.voteCount ?: 0
        )
    }
}