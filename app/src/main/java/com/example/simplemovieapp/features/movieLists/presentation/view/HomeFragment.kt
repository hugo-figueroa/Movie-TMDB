package com.example.simplemovieapp.features.movieLists.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.core.base.fragment.BaseFragment
import com.example.core.extensionFunctions.viewBinding
import com.example.material.MovieTHDBTheme
import com.example.simplemovieapp.databinding.FragmentHomeBinding
import com.example.simplemovieapp.features.movieLists.presentation.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * HomeFragment
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@AndroidEntryPoint
class HomeFragment() : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()

    override val binding: FragmentHomeBinding by viewBinding {
        FragmentHomeBinding.inflate(
            layoutInflater
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeContent.apply {
            setContent {
                MovieTHDBTheme {
                    HomeScreen(viewModel)
                }
            }
        }
    }
}