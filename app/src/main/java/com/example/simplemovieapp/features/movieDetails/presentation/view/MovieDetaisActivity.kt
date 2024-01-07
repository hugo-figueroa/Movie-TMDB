package com.example.simplemovieapp.features.movieDetails.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.core.base.activity.BaseActivity
import com.example.core.base.viewModel.EmptyViewModel
import com.example.core.extensionFunctions.viewBinding
import com.example.simplemovieapp.R
import com.example.simplemovieapp.databinding.ActivityMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * MovieDetailActivity
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@AndroidEntryPoint
class MovieDetaisActivity : BaseActivity<ActivityMovieDetailsBinding, EmptyViewModel>() {

    override val viewModel: EmptyViewModel by viewModels()

    override val binding: ActivityMovieDetailsBinding by viewBinding(ActivityMovieDetailsBinding::inflate)

    override fun setUp(extras: Bundle?) {
        super.setUp(extras)
        overridePendingTransition(R.anim.enter_from_right, R.anim.none)
        initGraphStartDestination(extras)
    }

    private fun initGraphStartDestination(extras: Bundle?) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.movieDetailContainer) as NavHostFragment
        val hostFragmentNavController = navHostFragment.navController
        val graphInflater = hostFragmentNavController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph_movie_details)
        navGraph.setStartDestination(R.id.movieDetailFragment)
        hostFragmentNavController.setGraph(navGraph, extras)
    }
}