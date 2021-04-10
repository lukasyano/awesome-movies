package com.lukas.awesomemovies.repository

import com.lukas.awesomemovies.data.network.MoviesService
import com.lukas.awesomemovies.repository.entity.MovieEntity
import com.lukas.awesomemovies.repository.mapper.Mapper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchMoviesRepository(private val moviesService: MoviesService) {

    fun getMoviesFromSearch(query: String): Single<List<MovieEntity>> {
        val response = moviesService.getMoviesFromSearch(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        return response.map {
            Mapper.mapMovies(it.results)
        }
    }
}