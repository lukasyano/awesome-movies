package com.lukas.awesomemovies.data.network

import com.lukas.awesomemovies.data.TrendingMoviesResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface MoviesService {
    @GET("trending/movie/week")
    fun getFeaturedMovies() : Observable<TrendingMoviesResponse>
}