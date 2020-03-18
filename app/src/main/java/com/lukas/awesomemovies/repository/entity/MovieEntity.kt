package com.lukas.awesomemovies.repository.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
//    @Embedded(prefix = "genre_")
//    val genreIds: List<Int>,
    val popularity: Double,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val posterPath: String,
    var isBookmarked: Boolean
)
