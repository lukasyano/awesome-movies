package com.lukas.awesomemovies.ui.home

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukas.awesomemovies.FilterType
import com.lukas.awesomemovies.FilterType.*
import com.lukas.awesomemovies.di.FILTER_TYPE_KEY
import com.lukas.awesomemovies.getEnum
import com.lukas.awesomemovies.putEnum
import com.lukas.awesomemovies.repository.BookmarksRepository
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel @ViewModelInject constructor(
    private val bookmarksRepository: BookmarksRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val liveData = MutableLiveData<HomeUiState>()
    val bookmarksLiveData = MutableLiveData<List<Int>>()
    private var bag = CompositeDisposable()

    init {
        val filterType = sharedPreferences.getEnum(FILTER_TYPE_KEY, UNFILTERED)
        getTrendingMovies(1, filterType)
    }

    fun onSwipeToRefresh() {
        val filterType = sharedPreferences.getEnum(FILTER_TYPE_KEY, UNFILTERED)
        getTrendingMovies(1, filterType)
    }

    private fun getTrendingMovies(pageNr: Int = 1, filterType: FilterType) {

        when (filterType) {
            POPULARITY -> liveData.postValue(HomeUiState.DisplayFilterLabel("MOST POPULAR MOVIES"))
            VOTES -> liveData.postValue(HomeUiState.DisplayFilterLabel("BEST RATED MOVIES"))
            DATE -> liveData.postValue(HomeUiState.DisplayFilterLabel("LATEST MOVIES"))
            UNFILTERED -> liveData.postValue(HomeUiState.DisplayFilterLabel("UNFILTERED MOVIES"))
        }

//        val observable: Single<List<MovieEntity>> =
//            trendingMoviesRepository.getTrendingMovies(pageNr, filterType)
//                .doOnSuccess {
//                    getBookmarkedMoviesIds()
//                }
//
//        val disposable = observable
//            .subscribe(
//                { liveData.postValue(HomeUiState.Success(it)) },
//                { liveData.postValue(HomeUiState.Error(it.message.toString())) }
//            )

        //   bag.add(disposable)
    }

    private fun getBookmarkedMoviesIds() {
        val disposable = bookmarksRepository.getBookmarkedMoviesIds()
            .subscribe(
                { bookmarksLiveData.postValue(it) },
                { liveData.postValue(HomeUiState.Error(it.message.toString())) }
            )

        bag.add(disposable)
    }

    fun onUnselectedBookmarkBtnClick(movie: MovieEntity) {
        val disposable = bookmarksRepository.insertBookmarkingMovie(movie)
            .subscribe()

        bag.add(disposable)
    }

    fun onSelectedBookmarkBtnClick(movieId: Int) {
        val disposable = bookmarksRepository.deleteBookmarkingMovie(movieId)
            .subscribe()

        bag.add(disposable)
    }

    fun onFilterTypeClicked(filterType: FilterType) {
        sharedPreferences.edit().putEnum(FILTER_TYPE_KEY, filterType).apply()

        when (filterType) {
            UNFILTERED -> getTrendingMovies(filterType = UNFILTERED)
            POPULARITY -> getTrendingMovies(filterType = POPULARITY)
            VOTES -> getTrendingMovies(filterType = VOTES)
            DATE -> getTrendingMovies(filterType = DATE)
        }
    }

    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }
}

