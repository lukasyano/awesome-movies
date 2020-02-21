package com.lukas.awesomemovies.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import com.lukas.awesomemovies.AMoviesApplication
import com.lukas.awesomemovies.R
import com.lukas.awesomemovies.loadIntoBaseUrl
import com.lukas.awesomemovies.repository.entity.MovieDetailsEntity
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private val movieId by lazy {
        navArgs<DetailsActivityArgs>().value.movieId
    }

    private val detailsViewModel by viewModels<DetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        observeDetailsData()
        val application = application as AMoviesApplication
        detailsViewModel.getMovieDetails(application.moviesRepository, movieId)
    }

    private fun observeDetailsData() {
        detailsViewModel.liveData.observe(
            this, Observer {
                setUi(it)
            }
        )
    }

    @SuppressLint("SetTextI18n")
    private fun setUi(entity: MovieDetailsEntity) {
        mainImageV.loadIntoBaseUrl(entity.posterPath)
        details_movie_title.text = entity.title
        language.text = entity.originalLanguage
        years.text = entity.releaseDate
        duration.text = "${(entity.runtime / 60)}h ${(entity.runtime % 60)}min"
        describe.text = entity.overview
    }
}