package com.lukas.awesomemovies.repository

import com.lukas.awesomemovies.data.network.NetworkServiceGenerator
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object MoviesRepository {

    fun getTrendingMovies() : Observable<List<MovieEntity>> {
        val observable=
            NetworkServiceGenerator.service.getTrendingMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            return observable.map {
            Mapper.map(it)
                .sortedByDescending {
                        movieEntity -> movieEntity.popularity
                }
        }
    }

}