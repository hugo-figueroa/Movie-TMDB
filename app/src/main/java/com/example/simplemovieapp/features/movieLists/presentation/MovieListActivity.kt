package com.example.simplemovieapp.features.movieLists.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.core.base.activity.BaseActivity
import com.example.core.base.viewModel.EmptyViewModel
import com.example.core.extensionFunctions.viewBinding
import com.example.simplemovieapp.R
import com.example.simplemovieapp.databinding.ActivityMovieListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieListActivity @Inject constructor() :
    BaseActivity<ActivityMovieListBinding, EmptyViewModel>() {

    override val viewModel: EmptyViewModel by viewModels()

    override val binding: ActivityMovieListBinding by viewBinding(ActivityMovieListBinding::inflate)

    override fun setUp(extras: Bundle?) {
        super.setUp(extras)
        overridePendingTransition(R.anim.enter_from_right, R.anim.none)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.movieListContainer) as NavHostFragment
        val navController = navHostFragment.navController
        val graphInflater = navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph_movie_list)
        navGraph.setStartDestination(R.id.popularMoviesFragment)
        navController.setGraph(navGraph, intent.extras)
        binding.bottomNavigation.setupWithNavController(navController)
    }
}