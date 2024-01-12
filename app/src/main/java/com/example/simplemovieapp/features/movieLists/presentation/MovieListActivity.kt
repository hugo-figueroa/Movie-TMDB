package com.example.simplemovieapp.features.movieLists.presentation

import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.plusAssign
import androidx.navigation.ui.setupWithNavController
import com.example.core.base.activity.BaseActivity
import com.example.core.base.viewModel.EmptyViewModel
import com.example.core.extensionFunctions.viewBinding
import com.example.core.utils.KeepStateNavigator
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
        val navigator = KeepStateNavigator(this, navHostFragment.childFragmentManager, R.id.movieListContainer)
        val navController = findNavController(R.id.movieListContainer)
        navController.navigatorProvider += navigator
        navController.setGraph(R.navigation.nav_graph_movie_list)
        binding.bottomNavigation.setupWithNavController(navController)
    }

    fun goToHome() {
        binding.bottomNavigation.selectedItemId = R.id.popularMoviesFragment
    }
}