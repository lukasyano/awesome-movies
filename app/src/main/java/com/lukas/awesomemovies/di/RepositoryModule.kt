package com.lukas.awesomemovies.di

import com.lukas.awesomemovies.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single { TrendingMoviesRepository(get()) }
    single { SearchMoviesRepository(get()) }
    single { BookmarksRepository(get()) }
    single { DetailsRepository(get()) }
    single { GenreRepository(get()) }
}
