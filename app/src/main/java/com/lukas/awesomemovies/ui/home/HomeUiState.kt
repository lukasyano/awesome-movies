package com.lukas.awesomemovies.ui.home

import com.lukas.awesomemovies.repository.entity.MovieEntity

sealed class HomeUiState {

    data class Success(val movies: List<MovieEntity>) : HomeUiState()

    data class Error(val error: String) : HomeUiState()

    data class DisplayFilterLabel(val type: String) : HomeUiState()
}