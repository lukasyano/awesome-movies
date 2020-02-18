package com.lukas.awesomemovies.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukas.awesomemovies.repository.MoviesRepository
import com.lukas.awesomemovies.repository.entity.MovieDetailsEntity
import io.reactivex.disposables.Disposable

class DetailsViewModel : ViewModel() {
    var liveData : MutableLiveData<MovieDetailsEntity> = MutableLiveData()
    lateinit var disposable : Disposable

    fun getMovieDetails(movieId : Int){
      val observable=  MoviesRepository.getMovieDetails(movieId)

       disposable = observable
           .subscribe {
           liveData.postValue(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}