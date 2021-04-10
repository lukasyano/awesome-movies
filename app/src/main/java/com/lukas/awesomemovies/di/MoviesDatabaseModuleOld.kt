package com.lukas.awesomemovies.di

import androidx.room.Room
import com.lukas.awesomemovies.data.database.MoviesDatabase
import org.koin.dsl.module

val moviesDatabaseModule = module {
    single {
        Room.databaseBuilder(get(), MoviesDatabase::class.java, "Movies.db")
            .build().moviesDao()
    }
}
