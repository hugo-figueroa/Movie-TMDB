package com.example.simplemovieapp.features.movieDetails.presentation.view

import androidx.fragment.app.viewModels
import com.example.core.base.fragment.BaseFragment
import com.example.core.extensionFunctions.viewBinding
import com.example.simplemovieapp.databinding.FragmentMovieDetailsBinding
import com.example.simplemovieapp.features.movieDetails.presentation.viewModel.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * MovieDetailFragment
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding, MovieDetailsViewModel>() {

    override val viewModel: MovieDetailsViewModel by viewModels()

    override val binding: FragmentMovieDetailsBinding by viewBinding {
        FragmentMovieDetailsBinding.inflate(
            layoutInflater
        )
    }
}