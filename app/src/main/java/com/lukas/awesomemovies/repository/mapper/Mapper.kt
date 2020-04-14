package com.lukas.awesomemovies.repository.mapper

import com.lukas.awesomemovies.data.network.model.Movie
import com.lukas.awesomemovies.data.network.model.MovieDetailsResponse
import com.lukas.awesomemovies.repository.entity.MovieDetailsEntity
import com.lukas.awesomemovies.repository.entity.MovieEntity

object Mapper {

    fun mapMovies(results: List<Movie>): List<MovieEntity> {

        return results.map {
            MovieEntity(
                id = it.id ?: -1,
                title = it.title ?: "",
                popularity = it.popularity ?: 0.0,
                overview = it.overview ?: "",
                releaseDate = it.releaseDate ?: "",
                voteAverage = it.voteAverage ?: 0.0,
                voteCount = it.voteCount ?: 0,
                posterPath = it.posterPath ?: ""
            )
        }.filter {
            it.posterPath.isNotEmpty().and(!it.voteAverage.equals(0.0))
        }
    }

    fun mapMovieDetails(response: MovieDetailsResponse): MovieDetailsEntity {
        var originalLanguage = "Unknown"
        val genresList = response.genres?.map { it.name }

        response.spoken_languages?.forEach {
            if (it.iso_639_1 == response.original_language) {
                originalLanguage = it.name
            }
        }
        return MovieDetailsEntity(
            id = response.id ?: -1,
            genres = genresList ?: emptyList(),
            title = response.title ?: "",
            overview = response.overview ?: "",
            runtime = response.runtime ?: 0,
            originalLanguage = originalLanguage,
            posterPath = response.poster_path ?: "",
            releaseDate = response.release_date ?: "",
            movieAverage = response.vote_average ?: 0.0
        )
    }

}