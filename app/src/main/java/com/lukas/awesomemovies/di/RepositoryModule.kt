package com.lukas.awesomemovies.di

import com.lukas.awesomemovies.repository.MoviesRepository
import com.lukas.awesomemovies.repository.SearchMoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MoviesRepository(get(),get()) }
    single { SearchMoviesRepository(get()) }
}
