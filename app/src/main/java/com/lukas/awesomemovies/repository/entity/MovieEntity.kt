package com.lukas.awesomemovies.repository.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "bookmarkedMovies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val popularity: Double,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val posterPath: String
) : Serializable
