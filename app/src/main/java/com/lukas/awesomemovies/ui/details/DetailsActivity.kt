package com.lukas.awesomemovies.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.google.android.material.chip.Chip
import com.lukas.awesomemovies.R
import com.lukas.awesomemovies.loadIntoBaseUrl
import com.lukas.awesomemovies.repository.entity.MovieDetailsEntity
import com.lukas.awesomemovies.snack
import kotlinx.android.synthetic.main.activity_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private val movie by lazy {
        navArgs<DetailsActivityArgs>().value.movieEntity
    }

    private val detailsViewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        observeLiveData()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detailsViewModel.getBookmarkedMovieById(movie.id)
        detailsViewModel.getMovieDetails(movie.id)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                //finish() ???
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun observeLiveData() {
        detailsViewModel.liveData.observe(
            this, {
                when (it) {
                    is DetailsUiState.Success -> setUi(it.detailsEntity)
                    is DetailsUiState.Error -> root.snack(it.error)
                    DetailsUiState.SelectBookmarksButton -> bookmarksImageV.isSelected = true
                    DetailsUiState.DeselectBookmarksButton -> bookmarksImageV.isSelected = false
                }
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
        rating.text = entity.movieAverage.toString()

        entity.genres.forEach {
            val chip = Chip(this)
            chip.text = it
            chipGroup.addView(chip)
        }

        bookmarksImageV.setOnClickListener {
            val isBookmarked = bookmarksImageV.isSelected
            if (isBookmarked) {
                detailsViewModel.onSelectedButtonClick(movie.id)
            } else {
                detailsViewModel.onDeselectedButtonClick(movie)
            }
        }
    }
}
