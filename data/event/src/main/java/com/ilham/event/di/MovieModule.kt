package com.ilham.event.di

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
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    private const val API_READ_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhNTgxYzhhNWZhMDllOGEzZDRhOGYxOTRkMTcwMDM5NSIsIm5iZiI6MTc0NjU0MDYxNy4zNjUsInN1YiI6IjY4MWExODQ5YjA2NjFjNmRhNTkzNDg5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.GfZ2J2fMAyAKO-cX_8VlSYmI50CEVm8D8lmfn_YFDQw"
    private const val API_KEY = "a581c8a5fa09e8a3d4a8f194d1700395"

    @Provides
    @Singleton
    fun provideAuthInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $API_READ_ACCESS_TOKEN") // Gunakan token Anda
                    .build()
                return chain.proceed(newRequest)
            }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: Interceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor) // Tambahkan logging untuk debugging
            .build()
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
    fun provideMovieApiService(okHttpClient: OkHttpClient): MovieApiService {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(Constans.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MovieApiService::class.java)
    }

}