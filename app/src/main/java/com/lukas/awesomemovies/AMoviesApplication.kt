package com.lukas.awesomemovies

import android.app.Application
import com.lukas.awesomemovies.di.networkModule
import com.lukas.awesomemovies.di.repositoryModule
import com.lukas.awesomemovies.di.viewModelModule
import io.paperdb.Paper
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AMoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AMoviesApplication)
            modules(networkModule, repositoryModule, viewModelModule)
        }

        Paper.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}