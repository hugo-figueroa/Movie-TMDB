package com.example.simplemovieapp.features.favorites.presentation.view


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
import com.example.simplemovieapp.databinding.FragmentFavoritesBinding
import com.example.simplemovieapp.features.favorites.presentation.viewModels.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * FavoritesFragment
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>() {

    override val viewModel: FavoritesViewModel by viewModels()

    override val binding: FragmentFavoritesBinding by viewBinding {
        FragmentFavoritesBinding.inflate(
            layoutInflater
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addViewModelsObservers()
        binding.favoritesContent.apply {
            setContent {
                MovieTHDBTheme {
                    FavoritesScreen(viewModel)
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