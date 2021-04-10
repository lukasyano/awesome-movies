package com.lukas.awesomemovies.ui.bookmarks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukas.awesomemovies.repository.BookmarksRepository
import io.reactivex.disposables.CompositeDisposable

class BookmarksViewModel(private val repository: BookmarksRepository) : ViewModel() {

    var liveData = MutableLiveData<BookmarksUiState>()
    var bag: CompositeDisposable = CompositeDisposable()

    init {
        getBookmarkedMovies()
    }

    private fun getBookmarkedMovies() {
        val disposable = repository.getBookmarkedMovies()
            .subscribe(
                {
                    liveData.postValue(BookmarksUiState.Success(it))
                },
                { liveData.postValue(BookmarksUiState.Error(it.message.toString())) }
            )
        bag.add(disposable)
    }

    fun onBookmarksBtnClick(movieId: Int) {
        val disposable = repository.deleteBookmarkingMovie(movieId)
            .subscribe()

        bag.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }
}

