package com.lukas.awesomemovies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lukas.awesomemovies.repository.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

}