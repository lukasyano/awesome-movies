package com.lukas.awesomemovies.repository

import com.lukas.awesomemovies.data.database.MoviesDao
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchMoviesRepository(private val moviesDao: MoviesDao) {
    fun getMoviesBySearch(title : String) : Observable<List<MovieEntity>> {
        return moviesDao.getMovieByTitle(title)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateBookmark(movie: MovieEntity): Completable {
        val updatedMovie: MovieEntity = movie.run {
            copy(
                id = id,
                title = title,
                popularity = popularity,
                overview = overview,
                releaseDate = releaseDate,
                voteAverage = voteAverage,
                voteCount = voteCount,
                posterPath = posterPath,
                isBookmarked = !isBookmarked
            )
        }
        return moviesDao.updateMovie(updatedMovie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}