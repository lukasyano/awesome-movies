package com.lukas.awesomemovies.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.lukas.awesomemovies.R
import com.lukas.awesomemovies.data.network.model.Movie
import com.lukas.awesomemovies.loadIntoBaseUrl
import kotlinx.android.synthetic.main.movie_list_item.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MovieItemViewHolder>() {

    private var data: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_item, parent, false)
        return MovieItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val movie = data[position]

        holder.rootView.setOnClickListener {
            onRootItemClicked(movie,it)
        }
        holder.bookmarksImageView.setOnClickListener{
            onBookmarksItemClick(it)
        }
        holder.titleView.text = movie.title
        holder.descriptionView.text = movie.overview
        holder.mainImageView.loadIntoBaseUrl(movie.posterPath)
    }

    private fun onBookmarksItemClick(view: View) {
        Snackbar.make(view,"Issaugotas Bookmarkuose", Snackbar.LENGTH_LONG).show()
    }

    private fun onRootItemClicked(movie: Movie, view: View) {
        Snackbar.make(view, movie.popularity.toString(), Snackbar.LENGTH_LONG).show()
    }

    fun updateData(data: List<Movie>) {
        this.data = data
        notifyDataSetChanged()
    }

    class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var rootView = view.root
        var mainImageView = view.mainImageV
        var titleView = view.titleV
        var descriptionView = view.descriptionV
        var bookmarksImageView = view.bookmarksImageV
    }
}
