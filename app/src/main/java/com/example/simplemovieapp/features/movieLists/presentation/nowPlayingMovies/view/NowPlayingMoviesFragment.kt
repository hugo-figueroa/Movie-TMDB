package com.example.simplemovieapp.features.movieLists.presentation.nowPlayingMovies.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.core.base.fragment.BaseFragment
import com.example.core.extensionFunctions.navigateTo
import com.example.core.extensionFunctions.observe
import com.example.core.extensionFunctions.viewBinding
import com.example.core.models.NavigationEvent
import com.example.material.MovieTHDBTheme
import com.example.simplemovieapp.constants.Constants
import com.example.simplemovieapp.databinding.FragmentNowPlayingMoviesBinding
import com.example.simplemovieapp.features.movieLists.presentation.nowPlayingMovies.viewModel.NowPlayingMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * NowPlayingMoviesFragment
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@AndroidEntryPoint
class NowPlayingMoviesFragment :
    BaseFragment<FragmentNowPlayingMoviesBinding, NowPlayingMoviesViewModel>() {

    override val viewModel: NowPlayingMoviesViewModel by viewModels()

    override val binding: FragmentNowPlayingMoviesBinding by viewBinding {
        FragmentNowPlayingMoviesBinding.inflate(
            layoutInflater
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addViewModelsObservers()
        binding.nowPlayingMoviesContent.apply {
            setContent {
                MovieTHDBTheme {
                    NowPlayingMoviesScreen(viewModel)
                }
            }
        }
    }

    private fun addViewModelsObservers() = with(viewModel) {
        observe(onNavigationEvent) { onNavigateToFragment(it) }
    }

    private fun onNavigateToFragment(navigationEvent: NavigationEvent) {
        val bundle = Bundle()
        bundle.putInt(Constants.MOVIE_ID, viewModel.movieId)
        navigateTo(destination = navigationEvent.destinationId, arguments = bundle)
    }
}