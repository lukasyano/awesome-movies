package com.lukas.awesomemovies.data

import com.google.gson.Gson

class JsonParser {

     val response = Constants.fakeFeaturedMoviesResponse

    fun parseJsonMovies() : TrendingMoviesResponse{
        val gson : Gson = Gson().newBuilder().create()
        return gson.fromJson(response,TrendingMoviesResponse::class.java)
    }
}