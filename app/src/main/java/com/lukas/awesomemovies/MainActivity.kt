package com.lukas.awesomemovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavBar()
    }

    private fun setupActionBarWithNavBar() {
        val topLevelDestinationsId =
            setOf(R.id.navigation_home, R.id.navigation_discover, R.id.navigation_bookmarks)
        val appConfig = AppBarConfiguration(topLevelDestinationsId)
        val navController: NavController = findNavController(R.id.mainNavHostFragment)
        setupActionBarWithNavController(navController, appConfig)
        bottomNav.setupWithNavController(navController)
    }
}

