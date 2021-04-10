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
import com.lukas.awesomemovies.snack
import kotlinx.android.synthetic.main.fragment_bookmarks.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksFragment : Fragment(), BookmarksListener {

    private val bookmarksViewModel: BookmarksViewModel by viewModel()
    private val bookmarksAdapter = BookmarksAdapter(this)

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

        observeLiveData()
    }

    private fun observeLiveData() {
        bookmarksViewModel.liveData.observe(
            viewLifecycleOwner, {
                when (it) {
                    is BookmarksUiState.Success -> {
                        bookmarksAdapter.updateData(it.movies)
                        if (it.movies.isEmpty()) no_bookmarks.visibility = View.VISIBLE
                        else no_bookmarks.visibility = View.INVISIBLE
                    }
                    is BookmarksUiState.Error -> {
                        no_bookmarks.snack(it.error)
                    }
                    BookmarksUiState.HideNoBookmarksText -> TODO()
                    BookmarksUiState.ShowNoBookmarksText -> TODO()
                }
            }
        )
    }

    override fun onMovieClick(movie: MovieEntity) {
        view?.let {
            findNavController()
                .navigate(
                    BookmarksFragmentDirections.actionNavigationBookmarksToNavigationMovieDetails(
                        movie
                    )
                )
        }
    }

    override fun onBookmarkBtnClick(movieId: Int) {
        bookmarksViewModel.onBookmarksBtnClick(movieId)
    }


}