package com.lukas.awesomemovies.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

const val FILTER_TYPE_KEY = "FILTER_TYPE_KEY"

@Module
@InstallIn(ApplicationComponent::class)
object SharedPreferencesModule{

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext applicationContext: Context) : SharedPreferences =
        applicationContext.getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
}
