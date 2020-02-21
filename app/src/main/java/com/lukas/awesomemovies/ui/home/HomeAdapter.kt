package com.lukas.awesomemovies.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lukas.awesomemovies.R
import com.lukas.awesomemovies.loadIntoBaseUrl
import com.lukas.awesomemovies.repository.MoviesRepository
import com.lukas.awesomemovies.repository.entity.MovieEntity
import kotlinx.android.synthetic.main.movie_list_item.view.*

class HomeAdapter(private val repository: MoviesRepository) : RecyclerView.Adapter<HomeAdapter.MovieItemViewHolder>() {

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
            onRootItemClicked(movieEntity, it)
        }
        holder.bookmarksImageView.setOnClickListener {
            onBookmarksItemClick(movieEntity, it)
        }
        holder.titleView.text = movieEntity.title
        holder.descriptionView.text = movieEntity.overview
        holder.mainImageView.loadIntoBaseUrl(movieEntity.posterPath)
    }

    private fun onBookmarksItemClick(movie: MovieEntity, view: View) {
        repository.saveMovieToBookmarks(movie)
    }

    private fun onRootItemClicked(movie: MovieEntity, view: View) {
        view.findNavController()
            .navigate(
                HomeFragmentDirections.actionNavigationHomeToNavigationMovieDetails(movie.id)
            )
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
    }
}
