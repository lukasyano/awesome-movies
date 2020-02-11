package com.lukas.awesomemovies.repository

import android.util.Log
import com.lukas.awesomemovies.data.network.NetworkServiceGenerator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object MoviesRepository {

    fun getFavouriteMovies(){
        val moviesService = NetworkServiceGenerator.service
        val observable = moviesService.getFeaturedMovies()

        val disposable = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.d("asd", "Results $it") },
                { Log.d("asd", "Error $it") }
            )
    }
}