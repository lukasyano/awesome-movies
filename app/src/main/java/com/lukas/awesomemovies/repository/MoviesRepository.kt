package com.lukas.awesomemovies.repository

import android.annotation.SuppressLint
import com.lukas.awesomemovies.data.database.MoviesDao
import com.lukas.awesomemovies.data.network.MoviesService
import com.lukas.awesomemovies.repository.entity.MovieDetailsEntity
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesRepository(private val movieService: MoviesService, private val moviesDao: MoviesDao) {

    @SuppressLint("CheckResult")
    fun getTrendingMovies(): Observable<List<MovieEntity>> {
        val observable =
            movieService.getTrendingMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        val mappedMovies = observable.map {
                Mapper.mapTrendingMovies(it)
                    .sortedByDescending { movieEntity ->
                        movieEntity.popularity
                    }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        //        mapped.doOnNext {
//            moviesDao.insertAll(it)
//        }

        return mappedMovies

    }

    fun getMovieDetails(movieId: Int): Observable<MovieDetailsEntity> {
        val observable =
            movieService.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        return observable.map {
            Mapper.mapMovieDetails(it)
        }
    }

    fun getBookmarkedMovies(): Observable<List<Int>> {

        return moviesDao.readBookmarkedMoviesId()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}