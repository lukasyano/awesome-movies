package com.lukas.awesomemovies.repository

import com.lukas.awesomemovies.data.TrendingMoviesResponse
import com.lukas.awesomemovies.data.network.NetworkServiceGenerator
import io.reactivex.Observable

object MoviesRepository {

    fun getFavouriteMovies() : Observable<TrendingMoviesResponse> {
        return NetworkServiceGenerator.service.getFeaturedMovies()
    }
}