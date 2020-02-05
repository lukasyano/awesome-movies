package com.lukas.awesomemovies.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukas.awesomemovies.R
import com.lukas.awesomemovies.data.Movie
import kotlinx.android.synthetic.main.movie_list_item.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MovieItemViewHolder>() {

    private var data: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_item, parent, false)
        Log.d("onCreateViewHolder","onCreateViewHolder")
        return MovieItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("getItemCount","getItemCount")

        return data.size
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        Log.d("onBindViewHolder","onBindViewHolder")

        val movie = data[position]

        holder.titleView.text = movie.title
        holder.descriptionView.text = movie.overview
    }

    fun updateData(data : List<Movie>){
        Log.d("updateData","updateData")

        this.data = data
        notifyDataSetChanged()
    }

    class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mainImageView = view.mainImageV
        var titleView = view.titleV
        var descriptionView = view.descriptionV
        var bookmarksImageView = view.bookmarksImageV
    }
}
