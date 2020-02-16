package com.lukas.awesomemovies

import android.view.View
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import com.lukas.awesomemovies.data.Constants
import com.moviesprototype.GlideApp

fun ImageView.loadIntoBaseUrl(posterPath: String){
    GlideApp
        .with(this)
        .load(Constants.IMAGE_BASE_URL.plus(posterPath))
        .into(this)
}

fun View.snack(text : String){
    Snackbar.make(this,text,Snackbar.LENGTH_LONG).show()
}