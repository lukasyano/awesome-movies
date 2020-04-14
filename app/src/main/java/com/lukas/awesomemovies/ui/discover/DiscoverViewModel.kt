package com.lukas.awesomemovies.ui.discover

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukas.awesomemovies.repository.BookmarksRepository
import com.lukas.awesomemovies.repository.SearchMoviesRepository
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.disposables.CompositeDisposable

class DiscoverViewModel(
    private val searchMoviesRepository: SearchMoviesRepository,
    private val bookmarksRepository: BookmarksRepository
) : ViewModel() {

    var liveData = MutableLiveData<DiscoverUiState>()
    val bookmarksLiveData = MutableLiveData<List<Int>>()
    var bag = CompositeDisposable()

    fun searchMovies(query: String) {
        val disposable = searchMoviesRepository.getMoviesFromSearch(query).doOnSuccess {
            observeBookmarksLiveData()
        }
            .subscribe(
                { liveData.postValue(DiscoverUiState.Success(it)) },
                { liveData.postValue(DiscoverUiState.Error(it.message.toString())) }
            )
        bag.add(disposable)
    }

    private fun observeBookmarksLiveData() {
        val disposable = bookmarksRepository.getBookmarkedMoviesIds()
            .subscribe(
                { bookmarksLiveData.postValue(it) },
                { liveData.postValue(DiscoverUiState.Error(it.message.toString())) }
            )
        bag.add(disposable)

    }

    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }

    fun onUnSelectedBtnClick(movie: MovieEntity) {
        val disposable = bookmarksRepository.insertBookmarkingMovie(movie)
            .subscribe()

        bag.add(disposable)
    }

    fun onSelectedBtnClick(movieId: Int) {
        val disposable = bookmarksRepository.deleteBookmarkingMovie(movieId)
            .subscribe()

        bag.add(disposable)
    }
}