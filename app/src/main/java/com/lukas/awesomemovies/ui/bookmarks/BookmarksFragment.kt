package com.lukas.awesomemovies.ui.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukas.awesomemovies.R
import com.lukas.awesomemovies.repository.entity.MovieEntity
import com.lukas.awesomemovies.ui.MovieListener
import com.lukas.awesomemovies.ui.home.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_bookmarks.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksFragment : Fragment(), MovieListener {

    private val bookmarksViewModel: BookmarksViewModel by viewModel()
    private val bookmarksAdapter = MoviesAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bookmarks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookmarksRecyclerView.adapter = bookmarksAdapter
        bookmarksRecyclerView.layoutManager = LinearLayoutManager(context)

        bookmarksViewModel.loadData()
        observeLiveData()
    }

    private fun observeLiveData() {
        bookmarksViewModel.liveData.observe(
            viewLifecycleOwner, Observer {
                bookmarksAdapter.updateData(it)
            }
        )
    }

    override fun onMovieClick(movieId: Int) {
        view?.let {
            findNavController()
                .navigate(
                    BookmarksFragmentDirections.actionNavigationBookmarksToNavigationMovieDetails(
                        movieId
                    )
                )
        }
    }

    override fun onBookmarksClick(movie: MovieEntity) {
        bookmarksViewModel.updateBookmark(movie)
    }
}