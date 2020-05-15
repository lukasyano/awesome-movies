package com.lukas.awesomemovies.data.network.model

data class MoviesResponse(
    val page: Int?,
    val results: List<MovieResult>,
    val total_pages: Int?,
    val total_results: Int?
)