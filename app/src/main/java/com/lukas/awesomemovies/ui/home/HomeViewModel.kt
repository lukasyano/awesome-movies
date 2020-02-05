package com.lukas.awesomemovies.ui.home

import androidx.lifecycle.ViewModel
import com.lukas.awesomemovies.data.Movie
import com.lukas.awesomemovies.data.TrendingMoviesResponse
import com.lukas.awesomemovies.repository.MoviesRepository

class HomeViewModel : ViewModel() {

    fun getFavouriteMovies(): List<Movie> {
        return MoviesRepository.getFavouriteMovies()
    }
}