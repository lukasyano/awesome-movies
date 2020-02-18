package com.lukas.awesomemovies.ui.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import com.lukas.awesomemovies.R
import com.lukas.awesomemovies.loadIntoBaseUrl
import com.lukas.awesomemovies.repository.entity.MovieDetailsEntity
import com.lukas.awesomemovies.snack
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
        detailsViewModel.getMovieDetails(movieId)
    }

    private fun observeDetailsData(){
        detailsViewModel.liveData.observe(
            this, Observer {
                    setUi(it)
            }
        )
    }

    private fun setUi(entity : MovieDetailsEntity){
        mainImageV.loadIntoBaseUrl(entity.posterPath)
    }
}