package com.lukas.awesomemovies.ui.bookmarks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.lukas.awesomemovies.R
import com.lukas.awesomemovies.loadIntoBaseUrl
import com.lukas.awesomemovies.repository.entity.MovieEntity
import kotlinx.android.synthetic.main.movie_list_item.view.*

class BookmarksAdapter(private val listener: BookmarksListener) :
    RecyclerView.Adapter<BookmarksAdapter.MovieItemViewHolder>() {

    private var data = emptyList<MovieEntity>()

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
            listener.onMovieClick(movie)
        }

        holder.bookmarksImageView.isSelected = true

        holder.bookmarksImageView.setOnClickListener {
            listener.onBookmarkBtnClick(movie.id)
        }

        holder.titleView.text = movie.title
        holder.descriptionView.text = movie.overview
        holder.mainImageView.loadIntoBaseUrl(movie.posterPath)
        holder.ratingStarView.text = movie.voteAverage.toString()
    }

    fun updateData(data: List<MovieEntity>) {
        this.data = data

        notifyDataSetChanged()
    }

    class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var rootView: ConstraintLayout = view.root
        var mainImageView: ImageView = view.mainImageV
        var titleView: TextView = view.titleV
        var descriptionView: TextView = view.descriptionV
        var bookmarksImageView: ImageButton = view.bookmarksImageV
        var ratingStarView: TextView = view.rating
    }
}
