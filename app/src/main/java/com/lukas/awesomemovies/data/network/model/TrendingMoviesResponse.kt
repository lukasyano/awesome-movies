package com.lukas.awesomemovies.data.network.model

data class TrendingMoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)