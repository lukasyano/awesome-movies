package com.lukas.awesomemovies

import android.content.SharedPreferences
import android.view.View
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import com.lukas.awesomemovies.data.Constants
import com.moviesprototype.GlideApp
import timber.log.Timber

fun ImageView.loadIntoBaseUrl(posterPath: String) {
    GlideApp
        .with(this)
        .load(Constants.IMAGE_BASE_URL.plus(posterPath))
        .centerCrop()
        .into(this)
}

fun View.snack(text: String) {
    Snackbar.make(this, text, Snackbar.LENGTH_LONG).show()
}

fun logTimberWithTag(msg: Any, tag: String = "TIMBER_DEBUG_TAG") {
    Timber.tag(tag).d(msg.toString())
}

inline fun <reified T : Enum<T>> SharedPreferences.getEnum(key: String, default: T) =
    this.getInt(key, -1).let { if (it >= 0) enumValues<T>()[it] else default }

fun <T : Enum<T>> SharedPreferences.Editor.putEnum(key: String, value: T?) : SharedPreferences.Editor =
    this.putInt(key, value?.ordinal ?: -1)