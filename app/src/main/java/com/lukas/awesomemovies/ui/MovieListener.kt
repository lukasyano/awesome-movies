package com.lukas.awesomemovies.ui

import com.lukas.awesomemovies.repository.entity.MovieEntity

interface MovieListener {
    fun onMovieClick(movieId : Int)
    fun onBookmarksClick(movie: MovieEntity)
}
