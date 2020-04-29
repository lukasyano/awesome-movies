package com.lukas.awesomemovies.ui.discover

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukas.awesomemovies.repository.BookmarksRepository
import com.lukas.awesomemovies.repository.DiscoveryRepository
import com.lukas.awesomemovies.repository.SearchMoviesRepository
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.disposables.CompositeDisposable

class DiscoverViewModel(
    private val searchMoviesRepository: SearchMoviesRepository,
    private val bookmarksRepository: BookmarksRepository,
    private val discoveryRepository: DiscoveryRepository
) : ViewModel() {
    var liveData = MutableLiveData<DiscoverUiState>()
    var bag = CompositeDisposable()

init {
      getMovieGenres()
    }

    fun searchMovies(query: String) {
        val disposable = searchMoviesRepository.getMoviesFromSearch(query).doOnSuccess {
            getBookmarkedMoviesIds()
        }
            .subscribe(
                { liveData.postValue(DiscoverUiState.MovieEntityData(it)) },
                { liveData.postValue(DiscoverUiState.Error(it.message.toString())) }
            )
        bag.add(disposable)
    }

    private fun getBookmarkedMoviesIds() {
        val disposable = bookmarksRepository.getBookmarkedMoviesIds()
            .subscribe(
                { liveData.postValue(DiscoverUiState.BookmarksData(it)) },
                { liveData.postValue(DiscoverUiState.Error(it.message.toString())) }
            )
        bag.add(disposable)
    }

    private fun getMovieGenres() {
        val disposable = discoveryRepository.getMovieGenres().subscribe(
            { liveData.postValue(DiscoverUiState.GenresData(it)) },
            { liveData.postValue(DiscoverUiState.Error(it.message.toString())) }
        )

        bag.add(disposable)
    }

    fun onUnSelectedBtnClick(movie: MovieEntity) {
        val disposable = bookmarksRepository.insertBookmarkingMovie(movie)
            .subscribe()

        bag.add(disposable)
    }

    fun onSelectedBtnClick(movieId: Int) {
        val disposable = bookmarksRepository.deleteBookmarkingMovie(movieId)
            .subscribe()

        bag.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }
}