package com.lukas.awesomemovies.data.database

import androidx.room.*
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(movies: List<MovieEntity>): Completable

    @Update
    fun updateMovie(movie: MovieEntity): Completable

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun getAllMoviesByPopularity(): Observable<List<MovieEntity>>

    @Query("SELECT id FROM movies WHERE isBookmarked = 1")
    fun getBookmarkedMovieIds(): Observable<List<Int>>

    @Query("SELECT * FROM movies WHERE isBookmarked = 1")
    fun getBookmarkedMovies() : Observable<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieById(movieId: Int): Observable<MovieEntity>
}