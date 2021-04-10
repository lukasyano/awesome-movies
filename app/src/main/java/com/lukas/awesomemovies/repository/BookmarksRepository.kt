package com.lukas.awesomemovies.repository

import com.lukas.awesomemovies.data.database.MoviesDao
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BookmarksRepository(private val moviesDao: MoviesDao) {

    fun getBookmarkedMovies(): Observable<List<MovieEntity>> {
        return moviesDao.getBookmarkedMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertBookmarkingMovie(movie: MovieEntity): Completable {
        return moviesDao.insertBookmarkingMovie(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteBookmarkingMovie(movieId: Int): Completable {
        return moviesDao.deleteBookmarkingMovie(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getBookmarkedMovieById(movieId: Int): Observable<MovieEntity>{
        return moviesDao.getBookmarkedMovieById(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getBookmarkedMoviesIds() : Observable<List<Int>>{
        return moviesDao.getBookmarkedMoviesIds()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}