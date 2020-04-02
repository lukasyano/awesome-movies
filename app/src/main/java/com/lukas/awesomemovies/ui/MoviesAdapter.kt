package com.lukas.awesomemovies.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukas.awesomemovies.R
import com.lukas.awesomemovies.loadIntoBaseUrl
import com.lukas.awesomemovies.repository.entity.MovieEntity
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MoviesAdapter(private val listener: MovieListener, private val showBookmarkIcon: Boolean) :
    RecyclerView.Adapter<MoviesAdapter.MovieItemViewHolder>() {

    private var data: List<MovieEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_item, parent, false)
        return MovieItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val movieEntity = data[position]

        holder.rootView.setOnClickListener {
            listener.onMovieClick(movieEntity.id)
        }

        if (showBookmarkIcon) {
            holder.bookmarksImageView.isSelected = movieEntity.isBookmarked
            holder.bookmarksImageView.visibility = View.VISIBLE

            holder.bookmarksImageView.setOnClickListener {
                listener.onBookmarksClick(movieEntity)
            }
        }

        holder.titleView.text = movieEntity.title
        holder.descriptionView.text = movieEntity.overview
        holder.mainImageView.loadIntoBaseUrl(movieEntity.posterPath)
        holder.ratingStarView.text = movieEntity.voteAverage.toString()
    }

    fun updateData(data: List<MovieEntity>) {
        this.data = data
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