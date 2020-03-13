package com.lukas.awesomemovies.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukas.awesomemovies.logTimberWithTag
import com.lukas.awesomemovies.repository.MoviesRepository
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

class HomeViewModel(private val repository: MoviesRepository) : ViewModel() {

    var moviesLiveData: MutableLiveData<List<MovieEntity>> = MutableLiveData()
    var errorLiveData: MutableLiveData<String> = MutableLiveData()
    lateinit var disposable: Disposable
    lateinit var bookmarkDisposable: Disposable
    lateinit var bookmarkDeleteDisposable: Disposable

    fun onSwipeToRefresh() {
        getFavouriteMovies()
    }

    fun getFavouriteMovies() {
        val observable: Observable<List<MovieEntity>> =
            repository.getTrendingMovies()

        disposable = observable
            .subscribe(
                { moviesLiveData.postValue(it) },
                { errorLiveData.postValue(it.message) }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
        bookmarkDisposable.dispose()
        bookmarkDeleteDisposable.dispose()
    }

    fun onBookmarkIconClicked(movie: MovieEntity) {
        if (movie.isBookmarked) {
            deleteFromBookmark(movie)
        } else {
            saveBookmark(movie)
        }
    }

    private fun saveBookmark(movie: MovieEntity) {

    }

    private fun deleteFromBookmark(movie: MovieEntity) {

    }

}


