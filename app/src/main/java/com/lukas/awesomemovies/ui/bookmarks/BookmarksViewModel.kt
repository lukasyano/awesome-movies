package com.lukas.awesomemovies.ui.bookmarks

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukas.awesomemovies.logTimberWithTag
import com.lukas.awesomemovies.repository.MoviesRepository
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.disposables.CompositeDisposable

class BookmarksViewModel(private val repository: MoviesRepository) : ViewModel() {

    var liveData = MutableLiveData<BookmarksUiState>()

    var bag: CompositeDisposable = CompositeDisposable()

    fun getBookmarkedMovies() {
        val disposable = repository.getBookmarkedMovies()
            .subscribe(
                {
                    liveData.postValue(BookmarksUiState.Success(it))
                },
                { liveData.postValue(BookmarksUiState.Error(it.message.toString())) }
            )
        bag.add(disposable)
    }

    fun updateBookmark(movie: MovieEntity) {
        val disposable = repository.updateBookmark(movie)
            .subscribe(
                { logTimberWithTag("bookmark value ${movie.title} updated from fragment") },
                { logTimberWithTag("${it.message}") }
            )
        bag.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }

}