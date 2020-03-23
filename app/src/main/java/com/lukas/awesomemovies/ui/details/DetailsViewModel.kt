package com.lukas.awesomemovies.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukas.awesomemovies.logTimberWithTag
import com.lukas.awesomemovies.repository.MoviesRepository
import com.lukas.awesomemovies.repository.entity.MovieDetailsEntity
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.disposables.CompositeDisposable

class DetailsViewModel(private val repository: MoviesRepository) : ViewModel() {
    var liveData: MutableLiveData<MovieDetailsEntity> = MutableLiveData()
    var movieEntityLiveData: MutableLiveData<MovieEntity> = MutableLiveData()
    private val bag = CompositeDisposable()

    fun getMovieDetails(movieId: Int) {

        val disposable = repository.getMovieDetails(movieId)
            .subscribe({
                liveData.postValue(it)
            }, {
                logTimberWithTag("${it.message}")
            })

        bag.add(disposable)
    }

    fun getMovieById(movieId: Int) {
        val disposable = repository.getMovieById(movieId)
            .subscribe(
                {
                    movieEntityLiveData.postValue(it)
                    logTimberWithTag("Updated bookmarked movie value ${it.isBookmarked}")
                },
                { logTimberWithTag("${it.message}") }
            )
        bag.add(disposable)
    }

    fun onBookmarksButtonClick() {
        movieEntityLiveData.value?.let {
            val disposable = repository.updateBookmark(it)
                .subscribe(
                    {  logTimberWithTag("Updated bookmarked movie value")},
                    { logTimberWithTag("${it.message}") }
                )
            bag.add(disposable)
        }
    }

    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }

}