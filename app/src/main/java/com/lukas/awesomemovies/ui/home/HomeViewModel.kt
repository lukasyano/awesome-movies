package com.lukas.awesomemovies.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukas.awesomemovies.logTimberWithTag
import com.lukas.awesomemovies.repository.MoviesRepository
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(private val repository: MoviesRepository) : ViewModel() {

    var moviesLiveData: MutableLiveData<List<MovieEntity>> = MutableLiveData()
    var errorLiveData: MutableLiveData<String> = MutableLiveData()

    private var bag = CompositeDisposable()
    fun onSwipeToRefresh() {
        getFavouriteMovies()
    }

    fun getFavouriteMovies() {
        val observable: Observable<List<MovieEntity>> =
            repository.getTrendingMovies()

        val disposable = observable
            .subscribe(
                { moviesLiveData.postValue(it) },
                { errorLiveData.postValue(it.message) }
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

