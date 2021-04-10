package com.lukas.awesomemovies.ui

import com.lukas.awesomemovies.repository.entity.MovieEntity

interface MovieListener {
    fun onMovieClick(movie: MovieEntity)
    fun onUnselectedBookmarkBtnClick(movie: MovieEntity)
    fun onSelectedBookmarkBtnClick(movieId: Int)
}
