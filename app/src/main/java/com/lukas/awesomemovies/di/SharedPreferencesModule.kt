package com.lukas.awesomemovies.di

import android.content.Context
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

const val FILTER_TYPE_KEY = "FILTER_TYPE_KEY"

val sharedPreferencesModule = module {
    single {
        androidApplication().getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
    }
}
