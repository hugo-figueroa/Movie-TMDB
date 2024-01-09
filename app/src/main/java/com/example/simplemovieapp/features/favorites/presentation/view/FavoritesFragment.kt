package com.example.simplemovieapp.features.favorites.presentation.view


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.core.base.fragment.BaseFragment
import com.example.core.extensionFunctions.viewBinding
import com.example.material.MovieTHDBTheme
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
        binding.favoritesContent.apply {
            setContent {
                MovieTHDBTheme {
                    FavoritesScreen(viewModel)
                }
            }
        }
    }
}