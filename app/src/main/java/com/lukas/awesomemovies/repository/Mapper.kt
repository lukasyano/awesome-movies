package com.lukas.awesomemovies.repository

import com.lukas.awesomemovies.data.network.model.MovieDetailsResponse
import com.lukas.awesomemovies.data.network.model.TrendingMoviesResponse
import com.lukas.awesomemovies.repository.entity.MovieDetailsEntity
import com.lukas.awesomemovies.repository.entity.MovieEntity

object Mapper {

    fun mapTrendingMovies(response: TrendingMoviesResponse): List<MovieEntity> {
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

    fun mapMovieDetails(response: MovieDetailsResponse): MovieDetailsEntity {
        var originalLanguage = "Unknown"
        val genres = response.genres.map { it.name }

        response.spoken_languages.forEach {
            if (it.iso_639_1 == response.original_language) {
                originalLanguage = it.name
            }
        }
        return MovieDetailsEntity(
            id = response.id,
            genres = genres,
            title = response.title,
            overview = response.overview,
            runtime = response.runtime,
            originalLanguage = originalLanguage,
            posterPath = response.poster_path,
            releaseDate = response.release_date
        )
    }
}