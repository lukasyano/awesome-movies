package com.lukas.awesomemovies.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lukas.awesomemovies.R
import com.lukas.awesomemovies.data.Movie
import kotlinx.android.synthetic.main.fragment_home.recycleView

class HomeFragment : Fragment() {

    val homeViewModel by viewModels<HomeViewModel>()
    val adapter = HomeAdapter()

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
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.dummy_data_menu_item -> loadData()
        }
        return false
    }

    fun loadData() {
        val movies = homeViewModel.getFavouriteMovies()
        adapter.updateData(movies)
    }
}