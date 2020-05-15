package com.lukas.awesomemovies.di

import com.lukas.awesomemovies.ui.bookmarks.BookmarksViewModel
import com.lukas.awesomemovies.ui.details.DetailsViewModel
import com.lukas.awesomemovies.ui.discover.DiscoverViewModel
import com.lukas.awesomemovies.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { DetailsViewModel(get(), get()) }
    viewModel { BookmarksViewModel(get()) }
    viewModel { DiscoverViewModel(get(), get(), get()) }
}