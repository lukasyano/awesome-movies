package com.lukas.awesomemovies

import android.app.Application
import com.lukas.awesomemovies.data.network.MoviesService
import com.lukas.awesomemovies.data.network.NetworkServiceGenerator
import com.lukas.awesomemovies.repository.MoviesRepository
import io.paperdb.Paper
import timber.log.Timber

class AMoviesApplication : Application() {

    lateinit var moviesRepository: MoviesRepository

    override fun onCreate() {
        super.onCreate()
        val movieService = NetworkServiceGenerator().retrofit.create(MoviesService::class.java)
        moviesRepository = MoviesRepository(movieService)
        Paper.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}