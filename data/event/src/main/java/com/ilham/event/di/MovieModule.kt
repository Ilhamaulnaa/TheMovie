package com.ilham.event.di

import androidx.compose.ui.unit.fontscaling.FontScaleConverterFactory
import com.ilham.event.common.data.ApiMapper
import com.ilham.event.movie.data.mapper_impl.MovieApiMapperImpl
import com.ilham.event.movie.data.remote.api.MovieApiService
import com.ilham.event.movie.data.remote.models.MovieDto
import com.ilham.event.movie.data.repository_impl.MovieRepositoryImpl
import com.ilham.event.movie.domain.models.Movie
import com.ilham.event.movie.domain.repository.MovieRepository
import com.ilham.event.util.Constans
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApiService: MovieApiService,
        mapper: ApiMapper<List<Movie>, MovieDto>
    ): MovieRepository = MovieRepositoryImpl(
        movieApiService = movieApiService,
        apiMapper = mapper
    )

    @Provides
    @Singleton
    fun provideMovieMapper(): ApiMapper<List<Movie>, MovieDto> = MovieApiMapperImpl()

    @Provides
    @Singleton
    fun provideMovieApiService(): MovieApiService {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(Constans.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(MovieApiService::class.java)
    }

}