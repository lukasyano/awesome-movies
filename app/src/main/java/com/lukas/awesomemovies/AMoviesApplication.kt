package com.lukas.awesomemovies

import android.app.Application
import com.lukas.awesomemovies.data.network.MoviesService
import com.lukas.awesomemovies.data.network.NetworkServiceGenerator
import io.paperdb.Paper
import timber.log.Timber

class AMoviesApplication : Application() {

    lateinit var movieService: MoviesService

    override fun onCreate() {
        super.onCreate()
        movieService = NetworkServiceGenerator().retrofit.create(MoviesService::class.java)

        Paper.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}