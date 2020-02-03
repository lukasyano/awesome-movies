package com.lukas.awesomemovies.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukas.awesomemovies.R
import com.lukas.awesomemovies.data.Movie
import kotlinx.android.synthetic.main.movie_list_item.view.*

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.MovieItemViewHolder>() {

    var data : List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_item,parent,false)
        return MovieItemViewHolder(view)
    }

    override fun getItemCount(): Int = 100


    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.titleView.text = "Rembo"
        holder.descriptionView.text = "Rembo negrizo"
    }

    class MovieItemViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var mainImageView = view.mainImageV
        var titleView = view.titleV
        var descriptionView = view.descriptionV
        var bookmarksImageView = view.bookmarksImageV
    }
}
