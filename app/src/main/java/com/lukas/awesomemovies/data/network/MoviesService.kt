package com.lukas.awesomemovies.data.network

import com.lukas.awesomemovies.data.network.model.MovieDetailsResponse
import com.lukas.awesomemovies.data.network.model.TrendingMoviesResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET("trending/movie/week")
    fun getTrendingMovies(@Query("pageNr") pageNr: Int) : Observable<TrendingMoviesResponse>

    @GET("movie/{movieId}")
    fun getMovieDetails(@Path("movieId") movieId : Int) : Single<MovieDetailsResponse>

}
