package com.lukas.awesomemovies

import com.lukas.awesomemovies.data.network.model.*


val fakeMovieDetailsResult = MovieDetailsResponse(
   adult = true,
    backdrop_path = "path",
    belongs_to_collection = null,
    budget = 1000,
    genres = null,
    homepage = "homepage",
    id = 99,
    imdb_id = "9.7",
    original_language = "English", // not empty
    original_title = "original_title",
    overview = "overview",
    popularity = 10.0,
    poster_path = "poster_path",
    production_companies = null,
    production_countries = null,
    release_date = "1998-04-06",
    revenue = 10000,
    runtime = 10000,
    spoken_languages = listOf(SpokenLanguage("Albania","Albania"),SpokenLanguage("Lithuania","Lithuania")),
    status = "status",
    tagline = "tagline",
    title = "title",
    video = true,
    vote_average = 10.0,
    vote_count = 1000
)



