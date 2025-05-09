package com.ilham.event.movie_detail.data.remote.api

import com.ilham.event.BuildConfig
import com.ilham.event.movie.data.remote.models.MovieDto
import com.ilham.event.movie_detail.data.remote.models.MovieDetailDto
import com.ilham.event.util.Constans
import com.ilham.event.util.Constans.MOVIE_ID
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val MOVIE_ID = "movie_id"
interface MovieDetailApiService {

    @GET("${Constans.MOVIE_DETAIL_ENDPOINT}/{${Constans.MOVIE_ID}}")
    suspend fun fetchMovieDetail(
        @Path(Constans.MOVIE_ID) movieId:Int,
        @Query("api_key") apiKey: String = BuildConfig.apiKey,
        @Query("append_to_response") appendToResponse: String = "credits,reviews"
    ): MovieDetailDto

    @GET("${Constans.MOVIE_ENDPOINT}")
    suspend fun fetchMovie(
        @Query("api_key") apiKey: String = BuildConfig.apiKey,
        @Query("include_adult") includeAdult: Boolean = false
    ): MovieDto
}