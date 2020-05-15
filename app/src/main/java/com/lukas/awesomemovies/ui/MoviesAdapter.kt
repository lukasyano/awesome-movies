package com.lukas.awesomemovies.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukas.awesomemovies.R
import com.lukas.awesomemovies.loadIntoBaseUrl
import com.lukas.awesomemovies.repository.entity.MovieEntity
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MoviesAdapter(private val listener: MovieListener) :
    RecyclerView.Adapter<MoviesAdapter.MovieItemViewHolder>() {

    private var data = emptyList<MovieEntity>()
    private var bookmarkedMoviesIds = emptyList<Int>()

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
        val isBookmarked = bookmarkedMoviesIds.contains(movie.id)

        holder.rootView.setOnClickListener {
            listener.onMovieClick(movie)
        }

        holder.bookmarksImageView.isSelected = isBookmarked

        holder.bookmarksImageView.setOnClickListener {
            if (isBookmarked) {
                listener.onSelectedBookmarkBtnClick(movie.id)
            } else {
                listener.onUnselectedBookmarkBtnClick(movie)
            }
        }

        holder.titleView.text = movie.title
        holder.descriptionView.text = movie.overview
        holder.mainImageView.loadIntoBaseUrl(movie.posterPath)
        holder.ratingStarView.text = movie.voteAverage.toString()
    }

    fun updateData(data: List<MovieEntity>? = null, bookmarksIds: List<Int>? = null) {
        data?.let {
            this.data = data
        }
        bookmarksIds?.let {
            this.bookmarkedMoviesIds = bookmarksIds
        }
        notifyDataSetChanged()
    }

    class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var rootView = view.root
        var mainImageView = view.mainImageV
        var titleView = view.titleV
        var descriptionView = view.descriptionV
        var bookmarksImageView = view.bookmarksImageV
        var ratingStarView = view.rating
    }
}