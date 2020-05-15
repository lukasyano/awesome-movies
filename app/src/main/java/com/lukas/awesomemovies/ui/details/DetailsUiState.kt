package com.lukas.awesomemovies.ui.details

import com.lukas.awesomemovies.repository.entity.MovieDetailsEntity

sealed class DetailsUiState {

    data class Success(val detailsEntity: MovieDetailsEntity) : DetailsUiState()

    data class Error(val error: String) : DetailsUiState()

    object SelectBookmarksButton : DetailsUiState()

    object DeselectBookmarksButton : DetailsUiState()
}