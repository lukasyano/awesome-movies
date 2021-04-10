package com.lukas.awesomemovies.ui.bookmarks

import com.lukas.awesomemovies.repository.entity.MovieEntity

interface BookmarksListener {
    fun onMovieClick(movie: MovieEntity)
    fun onBookmarkBtnClick(movieId: Int)
}

