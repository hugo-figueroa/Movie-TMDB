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

@AndroidEntryPoint
class MovieDetailsActivity : BaseActivity<ActivityMovieDetailsBinding, EmptyViewModel>() {

    private lateinit var arguments: Bundle

    override val viewModel: EmptyViewModel by viewModels()

    override val binding: ActivityMovieDetailsBinding by viewBinding(ActivityMovieDetailsBinding::inflate)

    override fun setUp(extras: Bundle?) {
        super.setUp(extras)
        arguments = extras ?: Bundle()
        overridePendingTransition(R.anim.enter_from_right, R.anim.none)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.movieDetailContainer) as NavHostFragment
        val naveController = navHostFragment.navController
        val inflater = naveController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph_movie_details)
        naveController.setGraph(graph, arguments)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.none, R.anim.slide_to_rigth)
    }
}