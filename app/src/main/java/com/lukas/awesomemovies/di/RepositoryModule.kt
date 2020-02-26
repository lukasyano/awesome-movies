package com.lukas.awesomemovies.di

import com.lukas.awesomemovies.repository.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MoviesRepository(get()) }
}
