package com.lukas.awesomemovies.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.lukas.awesomemovies.data.Movie
import com.lukas.awesomemovies.data.TrendingMoviesResponse
import com.lukas.awesomemovies.repository.MoviesRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    var moviesLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    var errorLiveData : MutableLiveData<Boolean> = MutableLiveData()
    lateinit var disposable: Disposable

    init {
        getFavouriteMovies()
    }

    private fun getFavouriteMovies() {
        val observable : Observable<TrendingMoviesResponse> = MoviesRepository.getFavouriteMovies()

        disposable = observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {moviesLiveData.postValue(it.results)},
                {errorLiveData.postValue(true)}
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}

