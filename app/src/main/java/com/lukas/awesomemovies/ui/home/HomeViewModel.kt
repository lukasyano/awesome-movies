package com.lukas.awesomemovies.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukas.awesomemovies.repository.MoviesRepository
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

class HomeViewModel : ViewModel() {

    var moviesLiveData: MutableLiveData<List<MovieEntity>> = MutableLiveData()
    var errorLiveData: MutableLiveData<String> = MutableLiveData()
    lateinit var disposable: Disposable

    init {
        getFavouriteMovies()
    }

    private fun getFavouriteMovies() {
        val observable: Observable<List<MovieEntity>> = MoviesRepository.getTrendingMovies()

        disposable = observable
            .subscribe(
                { moviesLiveData.postValue(it) },
                { errorLiveData.postValue(it.message) }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}

