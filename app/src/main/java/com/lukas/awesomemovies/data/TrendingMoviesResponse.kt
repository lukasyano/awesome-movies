package com.lukas.awesomemovies.data

data class TrendingMoviesResponse(
    val page: Int,
    val result: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)