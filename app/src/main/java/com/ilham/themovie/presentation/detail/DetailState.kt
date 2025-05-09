package com.ilham.themovie.presentation.detail

import com.ilham.event.movie.domain.models.Movie
import com.ilham.event.movie_detail.domain.models.MovieDetail

data class DetailState(

    val movieDetail: MovieDetail? = null,
    val movies: List<Movie> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false,
    val isMovieLoading: Boolean = false

)
