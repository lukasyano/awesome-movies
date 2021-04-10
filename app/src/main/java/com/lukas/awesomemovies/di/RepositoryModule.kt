package com.lukas.awesomemovies.di

import com.lukas.awesomemovies.data.database.MoviesDao
import com.lukas.awesomemovies.repository.BookmarksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideBookmarksRepository(moviesDao: MoviesDao) = BookmarksRepository(moviesDao)
}