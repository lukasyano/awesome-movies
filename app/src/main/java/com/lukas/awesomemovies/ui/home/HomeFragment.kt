package com.lukas.awesomemovies.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukas.awesomemovies.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val homeViewModel by viewModels<HomeViewModel>()
    private val adapter = HomeAdapter()

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
        observeMoviesLiveData()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
    }

    private fun observeMoviesLiveData() {
        homeViewModel.moviesLiveData.observe(
            viewLifecycleOwner,
            Observer {
                adapter.updateData(it)
            }
        )
    }
}