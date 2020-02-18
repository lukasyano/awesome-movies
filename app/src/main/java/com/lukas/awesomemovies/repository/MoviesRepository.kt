package com.lukas.awesomemovies.repository

import com.lukas.awesomemovies.data.network.NetworkServiceGenerator
import com.lukas.awesomemovies.data.network.model.MovieDetailsResponse
import com.lukas.awesomemovies.repository.entity.MovieDetailsEntity
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
            Mapper.mapTrendingMovies(it)
                .sortedByDescending {
                        movieEntity -> movieEntity.popularity
                }
        }
    }

    fun getMovieDetails(movieId : Int) : Observable<MovieDetailsEntity>{
        val observable =
            NetworkServiceGenerator.service.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        return observable.map {
            Mapper.mapMovieDetails(it)
        }
    }
}