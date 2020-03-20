package com.lukas.awesomemovies.repository

import com.lukas.awesomemovies.data.database.MoviesDao
import com.lukas.awesomemovies.data.network.MoviesService
import com.lukas.awesomemovies.data.network.model.TrendingMoviesResponse
import com.lukas.awesomemovies.logTimberWithTag
import com.lukas.awesomemovies.repository.entity.MovieDetailsEntity
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class MoviesRepository(private val movieService: MoviesService, private val moviesDao: MoviesDao) {

    fun getTrendingMovies(): Observable<List<MovieEntity>> {

        return Observable.zip(
                movieService.getTrendingMovies(),
                moviesDao.getBookmarkedMovieIds(),
                BiFunction<TrendingMoviesResponse, List<Int>, List<MovieEntity>>
                { response: TrendingMoviesResponse, bookmarkedIds: List<Int> ->
                    Mapper.mapTrendingMovies(response, bookmarkedIds)
                }
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                moviesDao.insertAll(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { logTimberWithTag("Completed") },
                        { logTimberWithTag(it.message.toString()) }
                    )
            }
            .flatMap {
                moviesDao.getAllMoviesByPopularity()
            }
    }

    fun getMovieDetails(movieId: Int): Observable<MovieDetailsEntity> {
        val observable =
            movieService.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        return observable.map {
            Mapper.mapMovieDetails(it)
        }
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