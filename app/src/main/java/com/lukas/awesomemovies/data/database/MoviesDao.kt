package com.lukas.awesomemovies.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lukas.awesomemovies.repository.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookmarkingMovie(movie: MovieEntity): Completable

    @Query("DELETE FROM bookmarkedMovies WHERE id = :movieId")
    fun deleteBookmarkingMovie(movieId: Int): Completable

    @Query("SELECT id FROM bookmarkedMovies")
    fun getBookmarkedMoviesIds(): Observable<List<Int>>

    @Query("SELECT * FROM bookmarkedMovies")
    fun getBookmarkedMovies(): Observable<List<MovieEntity>>

    @Query("SELECT * FROM bookmarkedMovies WHERE id = :movieId ")
    fun getBookmarkedMovieById(movieId: Int): Observable<MovieEntity>
}
