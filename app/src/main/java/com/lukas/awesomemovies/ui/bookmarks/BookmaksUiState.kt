package com.lukas.awesomemovies.ui.bookmarks

import com.lukas.awesomemovies.repository.entity.MovieEntity

sealed class BookmarksUiState {

    data class Success(val movies: List<MovieEntity>) : BookmarksUiState()

    data class Error(val error: String) : BookmarksUiState()

    object ShowNoBookmarksText : BookmarksUiState()

    object HideNoBookmarksText : BookmarksUiState()
}