package com.lukas.awesomemovies.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class DetailsViewModel : ViewModel() {
    var liveData : MutableLiveData<Any> = MutableLiveData()
    lateinit var disposable : Disposable

    fun getMovieDetails(movieId : Int){
      val observable=  Observable.just("Movies comming")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .delay(2,TimeUnit.SECONDS)

       disposable = observable.subscribe {
            liveData.postValue(it)
        }
    }
    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}