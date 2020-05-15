package com.lukas.awesomemovies.repository

import com.lukas.awesomemovies.data.network.MoviesService
import com.lukas.awesomemovies.data.network.model.MovieGenresResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GenreRepository(private val moviesService: MoviesService) {

    fun getMoviesGenres(): Single<MovieGenresResponse> {
        return moviesService.getMoviesGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}