package com.ilham.themovie.presentation.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilham.event.movie_detail.data.remote.api.MovieDetailApiService
import com.ilham.event.movie_detail.domain.repository.MovieDetailRepository
import com.ilham.event.util.Constans
import com.ilham.event.util.collectAndHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieDetailRepository,
    private val saveStateHandle: SavedStateHandle
): ViewModel() {

    private val _detailState = MutableStateFlow(DetailState())
    val detailState = _detailState.asStateFlow()

    val id: Int = saveStateHandle.get<Int>(Constans.MOVIE_ID) ?: -1

    init {
        fetchDetailMovieById()
    }

    private fun fetchDetailMovieById() = viewModelScope.launch {
        if (id == -1){
            _detailState.update {
                it.copy(isLoading = false, error = "Movie Not Found")
            }
        } else {
            repository.fetchMovieDetail(id).collectAndHandle(
                onError = { error ->
                    _detailState.update {
                        it.copy(isLoading = false, error = error?.message)
                    }
                },
                onLoading = {
                    _detailState.update {
                        it.copy(isLoading = true, error = null)
                    }
                }
            ){ movieDetail ->
                Log.d("MovieDetail", movieDetail.toString()) // ✅ Taruh di sini
                _detailState.update {
                    it.copy(
                        isLoading = false,
                        error = null,
                        movieDetail = movieDetail
                    )
                }
            }
        }
    }

    fun fetchMovie() = viewModelScope.launch {
        if (id == -1){
            _detailState.update {
                it.copy(isLoading = false, error = "Movie Not Found")
            }
        } else {
            repository.fetchMovie().collectAndHandle(
                onError = { error ->
                    _detailState.update {
                        it.copy(isMovieLoading = false, error = error?.message)
                    }
                },
                onLoading = {
                    _detailState.update {
                        it.copy(isMovieLoading = true, error = null)
                    }
                }
            ){ movie ->
                _detailState.update {
                    it.copy(
                        isMovieLoading = false,
                        error = null,
                        movies = movie
                    )
                }
            }
        }
    }

}