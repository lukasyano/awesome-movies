package com.lukas.awesomemovies.repository.entity

data class MovieEntity(
    val id: Int,
    val title: String,
    val genreIds: List<Int>,
    val popularity: Double,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int
    )
