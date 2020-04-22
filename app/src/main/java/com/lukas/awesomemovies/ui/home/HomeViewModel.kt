package com.lukas.awesomemovies.ui.home

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukas.awesomemovies.repository.BookmarksRepository
import com.lukas.awesomemovies.FilterType
import com.lukas.awesomemovies.repository.TrendingMoviesRepository
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
    private val trendingMoviesRepository: TrendingMoviesRepository,
    private val bookmarksRepository: BookmarksRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val liveData = MutableLiveData<HomeUiState>()
    val bookmarksLiveData = MutableLiveData<List<Int>>()

    private var bag = CompositeDisposable()

    init {
        getTrendingMovies()
    }

    fun onSwipeToRefresh() {
        getTrendingMovies()
    }

    private fun getTrendingMovies(pageNr: Int = 1, filterType: FilterType = FilterType.UNFILTERED) {

        val observable: Single<List<MovieEntity>> =
            trendingMoviesRepository.getTrendingMovies(pageNr, filterType)
                .doOnSuccess {
                    getBookmarkedMoviesIds()
                }

        val disposable = observable
            .subscribe(
                { liveData.postValue(HomeUiState.Success(it)) },
                { liveData.postValue(HomeUiState.Error(it.message.toString())) }
            )
        bag.add(disposable)
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

    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }

    fun onFilterTypeClicked(filterType: FilterType) {
        when (filterType) {
            FilterType.UNFILTERED -> getTrendingMovies(filterType = FilterType.UNFILTERED)
            FilterType.POPULARITY -> getTrendingMovies(filterType = FilterType.POPULARITY)
            FilterType.VOTES -> getTrendingMovies(filterType = FilterType.VOTES)
            FilterType.DATE -> getTrendingMovies(filterType = FilterType.DATE)
        }
    }
}

