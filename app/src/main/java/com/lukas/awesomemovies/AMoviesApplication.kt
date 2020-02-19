package com.lukas.awesomemovies

import android.app.Application
import com.lukas.awesomemovies.data.network.MoviesService
import com.lukas.awesomemovies.data.network.NetworkServiceGenerator

class AMoviesApplication : Application() {

    lateinit var movieService: MoviesService

    override fun onCreate() {
        super.onCreate()
        movieService = NetworkServiceGenerator().retrofit.create(MoviesService::class.java)
    }
}