package com.lukas.awesomemovies

import com.lukas.awesomemovies.data.network.model.MovieResult

/**
 * Fakes Api response which gives the list of MovieResult object
 */
val fakeListOfMovieResults = listOf(
    MovieResult( // this object should be filtered
        adult = null,
        backdropPath = null,
        genreIds = null,
        id = null,
        mediaType = null,
        originalLanguage = null,
        originalTitle = null,
        overview = null,
        popularity = null,
        posterPath = null,
        releaseDate = null,
        title = null,
        video = null,
        voteAverage = null,
        voteCount = null
    ),
    MovieResult( // this object should be filtered
        adult = true,
        backdropPath = "path",
        genreIds = listOf(1,2,3),
        id = 1,
        mediaType = "media",
        originalLanguage = "lang",
        originalTitle = "title",
        overview = "overview",
        popularity = 1.1,
        posterPath = "", // should be filtered because empty
        releaseDate = "date",
        title = "title",
        video = true,
        voteAverage = 0.0, // should be filtered because 0.0
        voteCount = 0
    ),
    MovieResult( // this object should not be filtered
        adult = true,
        backdropPath = "path",
        genreIds = listOf(1,2,3),
        id = 1,
        mediaType = "media",
        originalLanguage = "lang",
        originalTitle = "title",
        overview = "overview",
        popularity = 1.1,
        posterPath = "path", //not empty
        releaseDate = "date",
        title = "title",
        video = true,
        voteAverage = 1.0, // not 0.0
        voteCount = 0
    )
)