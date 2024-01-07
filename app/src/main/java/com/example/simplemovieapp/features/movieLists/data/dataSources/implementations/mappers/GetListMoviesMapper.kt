package com.example.simplemovieapp.features.movieLists.data.dataSources.implementations.mappers

import com.example.core.utils.Mapper
import com.example.simplemovieapp.features.movieLists.data.responseDto.ListMoviesDto
import com.example.simplemovieapp.features.movieLists.domain.models.MovieDomain
import com.example.simplemovieapp.features.movieLists.domain.models.ListMoviesDomain
import javax.inject.Inject

/**
 * GetPopularMoviesMapper
 *
 * @author (c) 2024, Hugo Figueroa
 * */
class GetListMoviesMapper @Inject constructor() : Mapper<ListMoviesDto, ListMoviesDomain> {
    override fun map(input: ListMoviesDto): ListMoviesDomain {
        return ListMoviesDomain(
            page = input.page,
            results = input.results.map { movieDto ->
                MovieDomain(
                    id = movieDto.id,
                    language = movieDto.language,
                    originalTitle = movieDto.originalTitle,
                    overview = movieDto.overview,
                    popularity = movieDto.popularity,
                    posterPath = movieDto.posterPath,
                    releaseDate = movieDto.releaseDate,
                    title = movieDto.title,
                    video = movieDto.video,
                    voteAverage = movieDto.voteAverage,
                    voteCount = movieDto.voteCount
                )
            },
            totalPages = input.totalPages,
            totalResults = input.totalResults
        )
    }
}