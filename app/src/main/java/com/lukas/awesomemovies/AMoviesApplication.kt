package com.lukas.awesomemovies

import android.app.Application
import com.lukas.awesomemovies.data.network.MoviesService
import com.lukas.awesomemovies.data.network.NetworkServiceGenerator
import com.lukas.awesomemovies.repository.MoviesRepository
import io.paperdb.Paper
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class AMoviesApplication : Application() {

     private val mainModule = module{
        single { MoviesRepository(get()) }
        single { NetworkServiceGenerator().retrofit.create(MoviesService::class.java) }
    }

    lateinit var moviesRepository: MoviesRepository

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AMoviesApplication)
            modules(mainModule)
        }

        val movieService = NetworkServiceGenerator().retrofit.create(MoviesService::class.java)
        moviesRepository = MoviesRepository(movieService)
        Paper.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}