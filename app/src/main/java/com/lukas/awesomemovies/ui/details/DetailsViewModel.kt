package com.lukas.awesomemovies.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukas.awesomemovies.repository.BookmarksRepository
import com.lukas.awesomemovies.repository.DetailsRepository
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.disposables.CompositeDisposable

class DetailsViewModel(
    private val detailsRepository: DetailsRepository,
    private val bookmarksRepository: BookmarksRepository
) : ViewModel() {


    var liveData: MutableLiveData<DetailsUiState> = MutableLiveData()
    private val bag = CompositeDisposable()

    fun getMovieDetails(movieId: Int) {

        val disposable = detailsRepository.getMovieDetails(movieId)
            .subscribe(
                { liveData.postValue(DetailsUiState.Success(it)) },
                { liveData.postValue(DetailsUiState.Error(it.message.toString())) })

        bag.add(disposable)
    }

    fun getBookmarkedMovieById(movieId: Int) {
        val disposable = bookmarksRepository.getBookmarkedMovieById(movieId)
            .subscribe(
                { liveData.postValue(DetailsUiState.SelectBookmarksButton) },
                { liveData.postValue(DetailsUiState.Error(it.message.toString())) })
        bag.add(disposable)
    }

    fun onSelectedButtonClick(movieId: Int) {
        val disposable = bookmarksRepository.deleteBookmarkingMovie(movieId)
            .subscribe(
                { liveData.postValue(DetailsUiState.DeselectBookmarksButton) },
                { liveData.postValue(DetailsUiState.SelectBookmarksButton) }
            )
        bag.add(disposable)
    }

    fun onDeselectedButtonClick(movie: MovieEntity) {
        val disposable = bookmarksRepository.insertBookmarkingMovie(movie)
            .subscribe(
                { liveData.postValue(DetailsUiState.SelectBookmarksButton) },
                { liveData.postValue(DetailsUiState.DeselectBookmarksButton) }
            )
        bag.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }

}