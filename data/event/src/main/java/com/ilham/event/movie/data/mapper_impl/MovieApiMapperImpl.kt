package com.ilham.event.movie.data.mapper_impl

import com.ilham.event.common.data.ApiMapper
import com.ilham.event.movie.data.remote.models.MovieDto
import com.ilham.event.movie.domain.models.Movie
import com.ilham.event.util.GenreConstants

class MovieApiMapperImpl: ApiMapper<List<Movie>, MovieDto> {
    override fun mapToDomain(apiDto: MovieDto): List<Movie> {
        return apiDto.results?.map { resultsItem ->
            Movie(
                backdropPath = formatEmptyValue(resultsItem?.backdropPath),
                genreIds = formatGenre(resultsItem?.genreIds),
                id = resultsItem?.id ?: 0,
                originalLanguage = formatEmptyValue(resultsItem?.originalLanguage),
                originalTitle = formatEmptyValue(resultsItem?.originalTitle),
                overview = formatEmptyValue(resultsItem?.overview),
                popularity = resultsItem?.popularity ?: 0.0,
                posterPath = formatEmptyValue(resultsItem?.posterPath),
                releaseDate = formatEmptyValue(resultsItem?.releaseDate, "date"),
                title = formatEmptyValue(resultsItem?.title, "title"),
                voteAverage = resultsItem?.voteAverage ?: 0.0,
                voteCount = resultsItem?.voteCount ?: 0,
                video = resultsItem?.video ?: false,
                adult = resultsItem?.adult ?: false
            )
        } ?: emptyList()
    }

    private fun formatEmptyValue(value: String?, default: String = ""): String {
        if (value.isNullOrEmpty()) return "Unknown $default"
        return value
    }

    private fun formatGenre(genreIds: List<Int?>?): List<String>{
        return genreIds?.map { GenreConstants.getGenreNameById(it ?: 0) } ?: emptyList()
    }

}