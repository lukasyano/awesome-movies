package com.lukas.awesomemovies.ui.discover

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Query
import com.lukas.awesomemovies.logTimberWithTag
import com.lukas.awesomemovies.repository.SearchMoviesRepository
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.disposables.CompositeDisposable

class DiscoverViewModel(private val searchMoviesRepository: SearchMoviesRepository) : ViewModel() {

    var liveData = MutableLiveData<DiscoverUiState>()
    var bag = CompositeDisposable()

    fun searchMovies(query : String) {
        val disposable = searchMoviesRepository.getMoviesFromSearch(query)
            .subscribe(
                { liveData.postValue(DiscoverUiState.Success(it)) },
                { liveData.postValue(DiscoverUiState.Error(it.message.toString())) }
            )
        bag.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }
}