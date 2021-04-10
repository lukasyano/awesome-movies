package com.lukas.awesomemovies.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukas.awesomemovies.FilterType
import com.lukas.awesomemovies.R
import com.lukas.awesomemovies.repository.entity.MovieEntity
import com.lukas.awesomemovies.snack
import com.lukas.awesomemovies.ui.MovieListener
import com.lukas.awesomemovies.ui.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), MovieListener {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesAdapter = MoviesAdapter(this)
        recycleView.adapter = moviesAdapter
        recycleView.layoutManager = LinearLayoutManager(context)

        observeLiveData()
        observeBookmarksLiveData()
        setupPullToRefresh()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter_by_popularity -> homeViewModel.onFilterTypeClicked(FilterType.POPULARITY)
            R.id.filter_by_date -> homeViewModel.onFilterTypeClicked(FilterType.DATE)
            R.id.filter_by_votes -> homeViewModel.onFilterTypeClicked(FilterType.VOTES)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observeLiveData() {
        homeViewModel.liveData.observe(
            viewLifecycleOwner, {
                when (it) {
                    is HomeUiState.Success -> {
                        recycleView.scrollToPosition(0)
                        moviesAdapter.updateData(it.movies)
                        swipeToRefresh.isRefreshing = false
                        spinner.hide()
                    }
                    is HomeUiState.Error -> {
                        recycleView.snack(it.error)
                        swipeToRefresh.isRefreshing = false
                        spinner.hide()
                    }
                    is HomeUiState.DisplayFilterLabel -> {
                        filterTypeView.visibility = View.VISIBLE
                        filterTypeView.text = it.labelText
                    }
                }
            }
        )
    }

    private fun observeBookmarksLiveData() {
        homeViewModel.bookmarksLiveData.observe(
            viewLifecycleOwner, {
                moviesAdapter.updateData(data = null, bookmarksIds = it)
            }
        )
    }

    private fun setupPullToRefresh() {
        swipeToRefresh.setOnRefreshListener {
            homeViewModel.onSwipeToRefresh()
        }
    }

    override fun onMovieClick(movie: MovieEntity) {

        view?.let {
            findNavController(it)
                .navigate(
                    HomeFragmentDirections.actionNavigationHomeToNavigationMovieDetails(movie)
                )
        }
    }

    override fun onUnselectedBookmarkBtnClick(movie: MovieEntity) {
        homeViewModel.onUnselectedBookmarkBtnClick(movie)
    }

    override fun onSelectedBookmarkBtnClick(movieId: Int) {
        homeViewModel.onSelectedBookmarkBtnClick(movieId)
    }
}
