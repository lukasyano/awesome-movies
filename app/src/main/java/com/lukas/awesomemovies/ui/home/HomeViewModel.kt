package com.lukas.awesomemovies.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukas.awesomemovies.data.network.model.Movie
import com.lukas.awesomemovies.data.network.model.TrendingMoviesResponse
import com.lukas.awesomemovies.repository.MoviesRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    var moviesLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    var errorLiveData: MutableLiveData<String> = MutableLiveData()
    lateinit var disposable: Disposable

    init {
        getFavouriteMovies()
    }

    private fun getFavouriteMovies() {
        val observable: Observable<TrendingMoviesResponse> = MoviesRepository.getTrendingMovies()

        disposable = observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { moviesLiveData.postValue(sortingMoviesByPopularity(it.results)) },
                { errorLiveData.postValue(it.message) }
            )
    }

    private fun sortingMoviesByPopularity(movies: List<Movie>): List<Movie> {
        return movies.sortedByDescending { movie -> movie.popularity }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}

