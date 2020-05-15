package com.lukas.awesomemovies.repository

import com.lukas.awesomemovies.data.network.MoviesService
import com.lukas.awesomemovies.repository.entity.MovieDetailsEntity
import com.lukas.awesomemovies.repository.mapper.Mapper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailsRepository(private val moviesService: MoviesService) {

    fun getMovieDetails(movieId: Int): Single<MovieDetailsEntity> {
        val single =
            moviesService.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        return single.map {
            Mapper.mapMovieDetails(it)
        }
    }

}