package com.lukas.awesomemovies.repository

import com.lukas.awesomemovies.data.network.model.TrendingMoviesResponse
import com.lukas.awesomemovies.repository.entity.MovieEntity

object Mapper {

    fun map(response: TrendingMoviesResponse): List<MovieEntity> {
        val movies = mutableListOf<MovieEntity>()
        response.results.forEach {
            val movieEntity = MovieEntity(
                id = it.id,
                title = it.title,
                genreIds = it.genreIds,
                popularity = it.popularity,
                overview = it.overview,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                posterPath = it.posterPath
            )
            movies.add(movieEntity)
        }
        return movies
    }
}