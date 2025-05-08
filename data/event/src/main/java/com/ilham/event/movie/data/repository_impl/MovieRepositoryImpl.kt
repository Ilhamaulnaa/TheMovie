package com.ilham.event.movie.data.repository_impl

import com.ilham.event.common.data.ApiMapper
import com.ilham.event.movie.data.remote.api.MovieApiService
import com.ilham.event.movie.data.remote.models.MovieDto
import com.ilham.event.movie.domain.models.Movie
import com.ilham.event.movie.domain.repository.MovieRepository
import com.ilham.event.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val movieApiService: MovieApiService,
    private val apiMapper: ApiMapper<List<Movie>, MovieDto>
):MovieRepository {
    override fun fetchDiscoverMovie(): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val movieDto = movieApiService.fetchDiscoverMovie()
        apiMapper.mapToDomain(movieDto).apply {
            emit(Response.Success(this))
        }
    }.catch{  e ->
        emit(Response.Error(e))
    }

    override fun fetchPopularMovie(): Flow<Response<List<Movie>>> = flow{
        emit(Response.Loading())
        val movieDto = movieApiService.fetchPopularMovie()
        apiMapper.mapToDomain(movieDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        emit(Response.Error(e))
    }


}