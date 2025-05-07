package com.ilham.event.movie.domain.repository

import com.ilham.event.movie.domain.models.Movie
import com.ilham.event.util.Response
import java.util.concurrent.Flow

interface MovieRepository {

    fun fetchDiscoverMovie(): kotlinx.coroutines.flow.Flow<Response<List<Movie>>>
    fun fetchPopularMovie(): kotlinx.coroutines.flow.Flow<Response<List<Movie>>>

}