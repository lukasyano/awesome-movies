package com.lukas.awesomemovies.repository.entity

data class MovieDetailsEntity(
    val id: Int,
    val genres: List<String>,
    val title: String,
    val overview: String,
    val runtime: Int,
    val originalLanguage: String,
    val posterPath: String,
    val releaseDate: String,
    val movieAverage: Double
)
