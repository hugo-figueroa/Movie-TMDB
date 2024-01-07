package com.example.simplemovieapp.features.movieLists.presentation.view

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.core.base.fragment.BaseFragment
import com.example.core.extensionFunctions.navigateTo
import com.example.core.extensionFunctions.setSafeOnClickListener
import com.example.core.extensionFunctions.viewBinding
import com.example.simplemovieapp.constants.Constants
import com.example.simplemovieapp.databinding.FragmentHomeBinding
import com.example.simplemovieapp.features.movieDetails.presentation.view.MovieDetaisActivity
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

    override fun addClicks() {
        binding.btnGoToDetails.setSafeOnClickListener {
            onNavigateToMovieDetails()
        }
    }

    private fun onNavigateToMovieDetails() {
        val bundle = Bundle()
        bundle.putInt(Constants.MOVIE_ID, 1029575)
        navigateTo<MovieDetaisActivity>(arguments = bundle)
    }
}