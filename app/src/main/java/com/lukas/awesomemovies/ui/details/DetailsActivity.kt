package com.lukas.awesomemovies.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import com.lukas.awesomemovies.R
import com.lukas.awesomemovies.loadIntoBaseUrl
import com.lukas.awesomemovies.repository.entity.MovieDetailsEntity
import kotlinx.android.synthetic.main.activity_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private val movieId by lazy {
        navArgs<DetailsActivityArgs>().value.movieId
    }

    private val detailsViewModel : DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        observeDetailsData()
        detailsViewModel.getMovieDetails(movieId)
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