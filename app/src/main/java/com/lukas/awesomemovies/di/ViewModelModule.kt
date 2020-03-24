package com.lukas.awesomemovies.di

import com.lukas.awesomemovies.ui.bookmarks.BookmarksViewModel
import com.lukas.awesomemovies.ui.details.DetailsViewModel
import com.lukas.awesomemovies.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { BookmarksViewModel(get()) }
}