package com.lukas.awesomemovies.repository

import com.lukas.awesomemovies.data.JsonParser
import com.lukas.awesomemovies.data.Movie

object MoviesRepository {

    fun getFavouriteMovies() : List<Movie> = JsonParser.parseJsonMovies().results

}