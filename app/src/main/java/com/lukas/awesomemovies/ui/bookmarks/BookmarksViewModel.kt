package com.lukas.awesomemovies.ui.bookmarks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukas.awesomemovies.logTimberWithTag
import com.lukas.awesomemovies.repository.MoviesRepository
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.disposables.CompositeDisposable

class BookmarksViewModel(private val repository: MoviesRepository) : ViewModel() {

    var liveData: MutableLiveData<List<MovieEntity>> = MutableLiveData()
    var bag: CompositeDisposable = CompositeDisposable()

    fun loadData() {
        val disposable = repository.getBookmarkedMovies()
            .subscribe(
                { liveData.postValue(it) },
                { logTimberWithTag(it.message.toString()) }
            )
        bag.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }

    fun updateBookmark(movie: MovieEntity) {
        repository.updateBookmark(movie)
    }
}