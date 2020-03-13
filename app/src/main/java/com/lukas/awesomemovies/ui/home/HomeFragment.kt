package com.lukas.awesomemovies.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukas.awesomemovies.R
import com.lukas.awesomemovies.repository.entity.MovieEntity
import com.lukas.awesomemovies.snack
import com.lukas.awesomemovies.ui.MovieListener
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() ,MovieListener{

    private val homeViewModel : HomeViewModel by viewModel()
    private lateinit var homeAdapter: HomeAdapter

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

        homeAdapter = HomeAdapter(this)
        recycleView.adapter = homeAdapter
        recycleView.layoutManager = LinearLayoutManager(context)
        observeMoviesLiveData()
        observeErrorLiveData()
        setupPullToRefresh()
        homeViewModel.getFavouriteMovies()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
    }

    private fun observeMoviesLiveData() {
        homeViewModel.moviesLiveData.observe(
            viewLifecycleOwner,
            Observer {
                homeAdapter.updateData(it)
                swipeToRefresh.isRefreshing = false
            }
        )
    }

    private fun observeErrorLiveData() {
        homeViewModel.errorLiveData.observe(
            viewLifecycleOwner,
            Observer { error ->
                recycleView.snack(error)
                swipeToRefresh.isRefreshing = false
            }
        )
    }

    private fun setupPullToRefresh() {
        swipeToRefresh.setOnRefreshListener {
            homeViewModel.onSwipeToRefresh()
        }
    }

    override fun onMovieClick(movieId: Int) {

        view?.let {
            findNavController(it)
                .navigate(
                    HomeFragmentDirections.actionNavigationHomeToNavigationMovieDetails(movieId)
                )
        }
    }

    override fun onBookmarksClick(movieId: MovieEntity){
        homeViewModel.onBookmarkIconClicked(movieId)
    }
}



