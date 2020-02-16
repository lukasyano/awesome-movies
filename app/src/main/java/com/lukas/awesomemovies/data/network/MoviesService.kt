package com.lukas.awesomemovies.data.network

import com.lukas.awesomemovies.data.network.model.TrendingMoviesResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface MoviesService {
    @GET("trending/movie/week")
    fun getTrendingMovies() : Observable<TrendingMoviesResponse>
}