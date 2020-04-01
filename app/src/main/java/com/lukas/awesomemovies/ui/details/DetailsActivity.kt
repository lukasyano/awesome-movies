package com.lukas.awesomemovies.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import com.google.android.material.chip.Chip
import com.lukas.awesomemovies.R
import com.lukas.awesomemovies.loadIntoBaseUrl
import com.lukas.awesomemovies.repository.entity.MovieDetailsEntity
import kotlinx.android.synthetic.main.activity_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private val movieId by lazy {
        navArgs<DetailsActivityArgs>().value.movieId
    }
    private val showBookmarksIcon by lazy {
        navArgs<DetailsActivityArgs>().value.showBookmarksIcon
    }

    private val detailsViewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        observeDetailsData()
        observeMovieEntityData()
        detailsViewModel.getMovieDetails(movieId)
        detailsViewModel.getMovieById(movieId)
    }

    private fun observeMovieEntityData() {
        detailsViewModel.movieEntityLiveData.observe(
            this, Observer {
                if (it.isBookmarked) {
                    add_to_bookmarks.text = getString(R.string.remove_from_bookmarks)
                } else {
                    add_to_bookmarks.text = getString(R.string.add_too_bookmarks)
                }
            }
        )
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
        language.text = entity.originalLanguage
        years.text = entity.releaseDate
        duration.text = "${(entity.runtime / 60)}h ${(entity.runtime % 60)}min"
        describe.text = entity.overview
        supportActionBar?.title = entity.title

        entity.genres.forEach {
            val chip = Chip(this)
            chip.text = it
            chipGroup.addView(chip)
        }
        if (showBookmarksIcon) {
            add_to_bookmarks.setOnClickListener {
                detailsViewModel.onBookmarksButtonClick()
            }
            add_to_bookmarks.visibility = View.VISIBLE
        }
    }
}