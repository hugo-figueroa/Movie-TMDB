package com.example.simplemovieapp.features.movieDetails.data.dataSources.implementations.mappers

import com.example.core.utils.Mapper
import com.example.simplemovieapp.features.movieDetails.data.responseDto.MovieDetailsDto
import com.example.simplemovieapp.features.movieDetails.domain.models.GenreDomain
import com.example.simplemovieapp.features.movieDetails.domain.models.LanguagesDomain
import com.example.simplemovieapp.features.movieDetails.domain.models.MovieDetailsDomain
import javax.inject.Inject

/**
 * GetMovieDetailsMapper
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class GetMovieDetailsMapper @Inject constructor() : Mapper<MovieDetailsDto, MovieDetailsDomain> {
    override fun map(input: MovieDetailsDto): MovieDetailsDomain {
        return MovieDetailsDomain(
            adult = input.adult,
            genres = input.genres.map { genreDto ->
                GenreDomain(
                    name = genreDto.name
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
            spokenLanguages = input.spokenLanguages.map { languagesDto ->
                LanguagesDomain(
                    name = languagesDto.name
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