package com.example.simplemovieapp.features.splash.presentation.viewModel

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.example.core.base.viewModel.BaseViewModel
import com.example.core.extensionFunctions.logInfo
import com.example.core.models.Result
import com.example.simplemovieapp.features.splash.domain.models.ConfigurationDomain
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

    override fun setUp(bundle: Bundle?) {
        super.setUp(bundle)
        getConfiguration()
    }

    private fun getConfiguration() {
        viewModelScope.launch {
            when (val resultCall = withContext(Dispatchers.IO) { getConfigurationUseCase() }) {
                is Result.Success<ConfigurationDomain> -> {
                    logInfo(resultCall.data.toString())
                }

                is Result.Error -> {}
            }
        }
    }
}