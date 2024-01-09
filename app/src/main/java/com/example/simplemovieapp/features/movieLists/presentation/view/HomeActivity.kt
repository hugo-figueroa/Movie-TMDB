package com.example.simplemovieapp.features.movieLists.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.core.base.activity.BaseActivity
import com.example.core.base.viewModel.EmptyViewModel
import com.example.core.extensionFunctions.viewBinding
import com.example.simplemovieapp.R
import com.example.simplemovieapp.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity @Inject constructor() : BaseActivity<ActivityHomeBinding, EmptyViewModel>() {

    override val viewModel: EmptyViewModel by viewModels()

    override val binding: ActivityHomeBinding by viewBinding(ActivityHomeBinding::inflate)

    override fun setUp(extras: Bundle?) {
        super.setUp(extras)
        overridePendingTransition(R.anim.enter_from_right, R.anim.none)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.homeContainer) as NavHostFragment
        val navController = navHostFragment.navController
        val graphInflater = navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph_home)
        navGraph.setStartDestination(R.id.homeFragment)
        navController.setGraph(navGraph, intent.extras)
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigation)
        navView.setupWithNavController(navController)
    }
}