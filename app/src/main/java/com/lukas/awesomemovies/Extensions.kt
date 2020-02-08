package com.lukas.awesomemovies

import android.widget.ImageView
import com.lukas.awesomemovies.data.Constants
import com.moviesprototype.GlideApp

fun ImageView.loadIntoBaseUrl(posterPath: String){
    GlideApp
        .with(this)
        .load(Constants.imageBaseUrl.plus(posterPath))
        .into(this)
}