package com.ilham.event.movie_detail.domain.repository

import com.ilham.event.movie.domain.models.Movie
import com.ilham.event.movie_detail.domain.models.MovieDetail
import com.ilham.event.util.Response
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    fun fetchMovieDetail(movieId: Int): Flow<Response<MovieDetail>>
    fun fetchMovie(): Flow<Response<List<Movie>>>

}