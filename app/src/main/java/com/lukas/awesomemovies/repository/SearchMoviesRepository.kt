package com.lukas.awesomemovies.repository

import com.lukas.awesomemovies.data.network.MoviesService
import com.lukas.awesomemovies.data.network.model.MoviesResponse
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.http.Query

class SearchMoviesRepository(private val moviesService: MoviesService) {

    fun getMoviesFromSearch(query : String) : Single<List<MovieEntity>> {
        val response = moviesService.getMoviesFromSearch(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        return response.map {
            Mapper.mapMovies(it.results, emptyList())
        }
    }

}