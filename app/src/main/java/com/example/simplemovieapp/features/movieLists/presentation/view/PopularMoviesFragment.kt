package com.example.simplemovieapp.features.movieLists.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.core.base.fragment.BaseFragment
import com.example.core.extensionFunctions.viewBinding
import com.example.material.MovieTHDBTheme
import com.example.simplemovieapp.databinding.FragmentPopularMoviesBinding
import com.example.simplemovieapp.features.movieLists.presentation.viewModel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * PopularMoviesFragment
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@AndroidEntryPoint
class PopularMoviesFragment() : BaseFragment<FragmentPopularMoviesBinding, MovieListViewModel>() {

    override val viewModel: MovieListViewModel by viewModels()

    override val binding: FragmentPopularMoviesBinding by viewBinding {
        FragmentPopularMoviesBinding.inflate(
            layoutInflater
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.popularMoviesContent.apply {
            setContent {
                MovieTHDBTheme {
                    MovieListScreen(viewModel)
                }
            }
        }
    }
}