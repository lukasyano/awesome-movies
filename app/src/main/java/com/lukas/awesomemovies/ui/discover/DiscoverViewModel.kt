package com.lukas.awesomemovies.ui.discover

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukas.awesomemovies.logTimberWithTag
import com.lukas.awesomemovies.repository.SearchMoviesRepository
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.disposables.CompositeDisposable

class DiscoverViewModel(private val repository: SearchMoviesRepository) : ViewModel() {

    var liveData = MutableLiveData<DiscoverUiState>()
    var bag = CompositeDisposable()

    fun searchMoviesByTitle(title: String) {
        val disposable = repository.getMoviesBySearch(title)
            .subscribe(
                { liveData.postValue(DiscoverUiState.Success(it)) },
                { liveData.postValue(DiscoverUiState.Error(it.message.toString())) }
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