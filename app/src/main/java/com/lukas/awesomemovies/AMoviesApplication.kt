package com.lukas.awesomemovies

import android.app.Application
import com.facebook.stetho.Stetho
import com.lukas.awesomemovies.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AMoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        startKoin {
            androidContext(this@AMoviesApplication)
            modules(
                networkModule,
                repositoryModule,
                viewModelModule,
                moviesDatabaseModule,
                sharedPreferencesModule
            )
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}