package com.lukas.awesomemovies.ui.discover

import com.lukas.awesomemovies.repository.entity.MovieEntity
import com.lukas.awesomemovies.repository.entity.MovieGenresEntity

sealed class DiscoverUiState {

    data class MovieEntityData(val movies: List<MovieEntity>) : DiscoverUiState()

    data class BookmarksData(val bookmarksIds: List<Int>) : DiscoverUiState()

    data class GenresData(val genres: List<MovieGenresEntity>) : DiscoverUiState()

    data class Error(val error: String) : DiscoverUiState()
}