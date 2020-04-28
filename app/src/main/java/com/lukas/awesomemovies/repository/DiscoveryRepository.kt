package com.lukas.awesomemovies.repository

import com.lukas.awesomemovies.data.network.MoviesService
import com.lukas.awesomemovies.repository.entity.MovieEntity
import com.lukas.awesomemovies.repository.mapper.Mapper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DiscoveryRepository(private val moviesService: MoviesService) {

    fun getDiscoveryMoviesByGenres(): Single<List<MovieEntity>> {
        return moviesService.getDiscoveryMoviesByGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { Mapper.mapMovies(it.results) }
    }
}