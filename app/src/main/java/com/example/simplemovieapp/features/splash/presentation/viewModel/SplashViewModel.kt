package com.example.simplemovieapp.features.splash.presentation.viewModel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.core.base.viewModel.BaseViewModel
import com.example.core.models.Result
import com.example.core.utils.SingleLiveEvent
import com.example.simplemovieapp.features.splash.domain.useCases.GetConfigurationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * SplashViewModel
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getConfigurationUseCase: GetConfigurationUseCase
) : BaseViewModel() {

    private val navigateToMovieListMLD = SingleLiveEvent<Unit>()
    val navigateToMovieList get(): LiveData<Unit> = navigateToMovieListMLD

    override fun setUp(bundle: Bundle?) {
        super.setUp(bundle)
        getConfiguration()
    }

    private fun getConfiguration() {
        viewModelScope.launch {
            when (withContext(Dispatchers.IO) { getConfigurationUseCase() }) {
                is Result.Success<Boolean> -> {
                    navigateToMovieListMLD.value = Unit
                }

                is Result.Error -> {
                    navigateToMovieListMLD.value = Unit
                }
            }
        }
    }
}