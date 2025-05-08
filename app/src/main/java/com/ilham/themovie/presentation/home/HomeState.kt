package com.ilham.themovie.presentation.home

import com.ilham.event.movie.domain.models.Movie

data class HomeState(

    val discoverMovie: List<Movie> = emptyList(),
    val popularMovie: List<Movie> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false

)