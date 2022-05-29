package com.lukas.awesomemovies.di

import com.lukas.awesomemovies.repository.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

val repositoryModule = module {
    single { TrendingMoviesRepository(get(),SchedulerProvider().io(), SchedulerProvider().ui()) }
    single { SearchMoviesRepository(get()) }
    single { BookmarksRepository(get()) }
    single { DetailsRepository(get()) }
    single { DiscoveryRepository(get()) }
}

class SchedulerProvider {

    fun io() = Schedulers.io()
    fun ui() = AndroidSchedulers.mainThread()
}