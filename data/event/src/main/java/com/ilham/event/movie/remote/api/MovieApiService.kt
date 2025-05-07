package com.ilham.event.movie.remote.api

import retrofit2.http.GET

interface MovieApiService {

    @GET
    suspend fun fetch()

}