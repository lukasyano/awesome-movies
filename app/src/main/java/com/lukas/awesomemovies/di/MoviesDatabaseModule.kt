package com.lukas.awesomemovies.di

import android.content.Context
import androidx.room.Room
import com.lukas.awesomemovies.data.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object MoviesDatabaseModule {

    @Singleton
    @Provides
    fun provideMoviesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MoviesDatabase::class.java, "Movies.db")
            .build()
            .moviesDao()
}
