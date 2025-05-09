package com.ilham.event.di

import com.ilham.event.common.data.ApiMapper
import com.ilham.event.movie.data.remote.api.MovieApiService
import com.ilham.event.movie.data.remote.models.MovieDto
import com.ilham.event.movie.data.repository_impl.MovieRepositoryImpl
import com.ilham.event.movie.domain.models.Movie
import com.ilham.event.movie.domain.repository.MovieRepository
import com.ilham.event.movie_detail.data.mapper_impl.MovieDetailMapperImpl
import com.ilham.event.movie_detail.data.remote.api.MovieDetailApiService
import com.ilham.event.movie_detail.data.remote.models.MovieDetailDto
import com.ilham.event.movie_detail.data.repository_impl.MovieDetailRepositoryImpl
import com.ilham.event.movie_detail.domain.models.MovieDetail
import com.ilham.event.movie_detail.domain.repository.MovieDetailRepository
import com.ilham.event.util.Constans
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailModule {

    @Provides
    @Singleton
    fun provideMovieDetailRepository(
        movieDetailApiService: MovieDetailApiService,
        movieDetailMapper: ApiMapper<MovieDetail, MovieDetailDto>,
        movieMapper: ApiMapper<List<Movie>, MovieDto>
    ): MovieDetailRepository = MovieDetailRepositoryImpl(
        movieDetailApiService = movieDetailApiService,
        apiDetailMapper = movieDetailMapper,
        apiMovieMapper = movieMapper
    )

    @Provides
    @Singleton
    fun provideMovieMapper(): ApiMapper<MovieDetail, MovieDetailDto> = MovieDetailMapperImpl()

    @Provides
    @Singleton
    fun provideMovieDetailApiService(okHttpClient: OkHttpClient): MovieDetailApiService {
        return Retrofit.Builder()
            .baseUrl(Constans.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MovieDetailApiService::class.java)
    }

}