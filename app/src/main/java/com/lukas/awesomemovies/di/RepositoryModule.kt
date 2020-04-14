package com.lukas.awesomemovies.di

import com.lukas.awesomemovies.repository.BookmarksRepository
import com.lukas.awesomemovies.repository.DetailsRepository
import com.lukas.awesomemovies.repository.TrendingMoviesRepository
import com.lukas.awesomemovies.repository.SearchMoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { TrendingMoviesRepository(get()) }
    single { SearchMoviesRepository(get()) }
    single { BookmarksRepository(get()) }
    single { DetailsRepository(get()) }
}
