package com.lukas.awesomemovies.repository

import com.lukas.awesomemovies.data.network.model.TrendingMoviesResponse
import com.lukas.awesomemovies.data.network.NetworkServiceGenerator
import io.reactivex.Observable

object MoviesRepository {

    fun getTrendingMovies() : Observable<TrendingMoviesResponse> {
        return NetworkServiceGenerator.service.getTrendingMovies()
    }
}