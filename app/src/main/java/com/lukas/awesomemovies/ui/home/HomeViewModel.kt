package com.lukas.awesomemovies.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukas.awesomemovies.data.Movie
import com.lukas.awesomemovies.repository.MoviesRepository

class HomeViewModel : ViewModel() {
    var moviesLiveData: MutableLiveData<List<Movie>> = MutableLiveData()

    init {
        getFavouriteMovies()
    }

    private fun getFavouriteMovies() {
        val favouriteMovies = MoviesRepository.getFavouriteMovies()
        moviesLiveData.postValue(favouriteMovies)
    }
}

