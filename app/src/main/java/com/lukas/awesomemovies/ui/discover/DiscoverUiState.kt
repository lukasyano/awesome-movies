package com.lukas.awesomemovies.ui.discover

import com.lukas.awesomemovies.repository.entity.MovieEntity

sealed class DiscoverUiState {

    data class Success(val movies: List<MovieEntity>) : DiscoverUiState()

    data class Error(val error: String) : DiscoverUiState()
}