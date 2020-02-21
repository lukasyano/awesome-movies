package com.lukas.awesomemovies.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukas.awesomemovies.AMoviesApplication
import com.lukas.awesomemovies.R
import com.lukas.awesomemovies.snack
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.movie_list_item.*

class HomeFragment : Fragment() {

    private val homeViewModel by viewModels<HomeViewModel>()
    lateinit var homeAdapter: HomeAdapter

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

        val application = activity!!.application as AMoviesApplication
        application.moviesRepository.let {
            homeAdapter = HomeAdapter(it)
            homeViewModel.setRepository(it)
        }

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val application = activity!!.application as AMoviesApplication
        root.snack(application.moviesRepository.readAllBookmarks().size.toString())
        return true
    }

    private fun observeMoviesLiveData() {
        homeViewModel.moviesLiveData.observe(
            viewLifecycleOwner,
            Observer { movieEntities ->
                homeAdapter.updateData(movieEntities)
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
}