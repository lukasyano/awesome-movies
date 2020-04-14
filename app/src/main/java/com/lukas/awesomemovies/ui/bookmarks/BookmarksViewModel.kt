package com.lukas.awesomemovies.ui.bookmarks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukas.awesomemovies.repository.BookmarksRepository
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.disposables.CompositeDisposable

class BookmarksViewModel(private val repository: BookmarksRepository) : ViewModel() {

    var liveData = MutableLiveData<BookmarksUiState>()
    var bookmarksLiveData = MutableLiveData<List<Int>>()

    var bag: CompositeDisposable = CompositeDisposable()

    init {
        getBookmarkedMovies()
    }

    private fun getBookmarkedMovies() {
        val disposable = repository.getBookmarkedMovies().doOnEach{
            getBookmarkedMoviesIds()
        }
            .subscribe(
                {
                    liveData.postValue(BookmarksUiState.Success(it))
                },
                { liveData.postValue(BookmarksUiState.Error(it.message.toString())) }
            )
        bag.add(disposable)
    }

    private fun getBookmarkedMoviesIds() {
        val disposable = repository.getBookmarkedMoviesIds()
            .subscribe(
                { bookmarksLiveData.postValue(it) },
                { liveData.postValue(BookmarksUiState.Error(it.message.toString())) }
            )
        bag.add(disposable)
    }


    fun onUnselectedBookmarkBtnClick(movie: MovieEntity) {
        val disposable = repository.insertBookmarkingMovie(movie)
            .subscribe()

        bag.add(disposable)
    }

    fun onSelectedBookmarkBtnClick(movieId: Int) {
        val disposable = repository.deleteBookmarkingMovie(movieId)
            .subscribe()

        bag.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }
}
