package com.lukas.awesomemovies.ui.discover

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.lukas.awesomemovies.R
import com.lukas.awesomemovies.repository.entity.MovieEntity
import com.lukas.awesomemovies.snack
import com.lukas.awesomemovies.ui.MovieListener
import com.lukas.awesomemovies.ui.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_discover.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscoverFragment : Fragment(), MovieListener {

    private val discoverViewModel: DiscoverViewModel by viewModel()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesAdapter = MoviesAdapter(this)
        recycleView.adapter = moviesAdapter
        recycleView.layoutManager = LinearLayoutManager(context)

        observeLiveData()
        setSearchView()
    }

    private fun observeLiveData() {
        discoverViewModel.liveData.observe(
            viewLifecycleOwner, Observer {
                when (it) {
                    is DiscoverUiState.MovieEntityData -> {
                        moviesAdapter.updateData(it.movies)
                        spinner.hide()
                        chipGroup.visibility = View.GONE
                        recycleView.visibility = View.VISIBLE
                    }
                    is DiscoverUiState.Error -> {
                        chipGroup.visibility = View.GONE
                        recycleView.visibility = View.GONE
                        recycleView.snack(it.error)
                        spinner.hide()
                    }
                    is DiscoverUiState.BookmarksData -> {
                        moviesAdapter.updateData(data = null, bookmarksIds = it.bookmarksIds)
                    }

                    is DiscoverUiState.GenresData -> {
                        chipGroup.visibility = View.VISIBLE
                        it.genres.forEach {
                            val chip = Chip(this.context)
                            chip.text = it.name
                            chipGroup.addView(chip)
                        }
                    }
                }
            }
        )
    }

    private fun setSearchView() {
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    discoverViewModel.searchMovies(query)
                    spinner.show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onMovieClick(movie: MovieEntity) {
        view?.let {
            findNavController(it)
                .navigate(
                    DiscoverFragmentDirections.actionNavigationDiscoverToNavigationMovieDetails(
                        movie
                    )
                )
        }
    }

    override fun onUnselectedBookmarkBtnClick(movie: MovieEntity) {
        discoverViewModel.onUnSelectedBtnClick(movie)
    }

    override fun onSelectedBookmarkBtnClick(movieId: Int) {
        discoverViewModel.onSelectedBtnClick(movieId)
    }

    override fun onPause() {
        super.onPause()

        val imm: InputMethodManager =
            activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }
}