package com.lukas.awesomemovies.data.network.model

data class MovieDiscoveryResponse(
    val page: Int,
    val results: List<MovieDiscoveryResult>,
    val total_pages: Int,
    val total_results: Int
)