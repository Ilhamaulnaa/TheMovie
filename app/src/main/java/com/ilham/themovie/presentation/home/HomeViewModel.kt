package com.ilham.themovie.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilham.event.movie.domain.models.Movie
import com.ilham.event.movie.domain.repository.MovieRepository
import com.ilham.event.util.collectAndHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository,
):ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    init {
        fetchDiscoverMovie()
    }
    init {
        fetchPopularMovie()
    }

    private fun fetchDiscoverMovie() = viewModelScope.launch {
        repository.fetchDiscoverMovie().collectAndHandle(
            onError = { error ->
                _homeState.update {
                    it.copy(isLoading = false, error = error?.message)
                }
            },
            onLoading = {
                _homeState.update {
                    it.copy(isLoading = true, error = null)
                }
            },
        ){ movie ->
            _homeState.update {
                it.copy(isLoading = false, error = null, discoverMovie = movie)
            }
        }
    }

    private fun fetchPopularMovie() = viewModelScope.launch {
        repository.fetchPopularMovie().collectAndHandle(
            onError = { error ->
                _homeState.update {
                    it.copy(isLoading = false, error = error?.message)
                }
            },
            onLoading = {
                _homeState.update {
                    it.copy(isLoading = true, error = null)
                }
            }
        ){ movie ->
            _homeState.update {
                it.copy(isLoading = false, error = null, popularMovie = movie)
            }
        }
    }

}