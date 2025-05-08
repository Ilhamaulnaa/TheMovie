package com.ilham.event.movie.data.remote.api

import com.ilham.event.BuildConfig
import com.ilham.event.movie.data.remote.models.MovieDto
import com.ilham.event.util.Constans
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface MovieApiService {

    @GET(Constans.MOVIE_ENDPOINT)
    suspend fun fetchDiscoverMovie(
        @Query("api_key") apiKey: String = BuildConfig.apiKey,
        @Query("include_adult") includeAdult: Boolean = false
    ): MovieDto

    @GET(Constans.POPULAR_MOVIE_ENDPOINT)
    suspend fun fetchPopularMovie(
        @Query("api_key") apiKey: String = BuildConfig.apiKey,
        @Query("include_adult") includeAdult: Boolean = false
    ): MovieDto

}