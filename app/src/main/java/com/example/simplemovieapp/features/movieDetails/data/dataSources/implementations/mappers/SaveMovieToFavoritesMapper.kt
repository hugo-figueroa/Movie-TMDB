package com.example.simplemovieapp.features.movieDetails.data.dataSources.implementations.mappers

import com.example.core.utils.Mapper
import com.example.simplemovieapp.features.favorites.data.entities.GenreEntity
import com.example.simplemovieapp.features.favorites.data.entities.LanguageEntity
import com.example.simplemovieapp.features.favorites.data.entities.MovieEntity
import com.example.simplemovieapp.features.movieDetails.domain.models.MovieDetailsDomain
import javax.inject.Inject

/**
 * SaveMovieToFavoritesMapper
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class SaveMovieToFavoritesMapper @Inject constructor() : Mapper<MovieDetailsDomain, MovieEntity> {
    override fun map(input: MovieDetailsDomain): MovieEntity {
        return MovieEntity(
            adult = input.adult,
            genres = input.genres.map { genreDomain ->
                GenreEntity(
                    name = genreDomain.name
                )
            },
            id = input.id,
            originalLanguage = input.originalLanguage,
            originalTitle = input.originalTitle,
            overview = input.overview,
            popularity = input.popularity,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            revenue = input.revenue,
            runtime = input.runtime,
            spokenLanguages = input.spokenLanguages.map { languageDomain ->
                LanguageEntity(
                    name = languageDomain.name
                )
            },
            status = input.status,
            title = input.title,
            video = input.video,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount
        )
    }
}