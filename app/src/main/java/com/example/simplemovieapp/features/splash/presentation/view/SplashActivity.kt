package com.example.simplemovieapp.features.splash.presentation.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.example.core.base.activity.BaseActivity
import com.example.core.extensionFunctions.navigateTo
import com.example.core.extensionFunctions.observe
import com.example.core.extensionFunctions.transparentStatusBarUI
import com.example.core.extensionFunctions.viewBinding
import com.example.simplemovieapp.databinding.ActivitySplashBinding
import com.example.simplemovieapp.features.movieLists.presentation.view.HomeActivity
import com.example.simplemovieapp.features.splash.presentation.constants.SplashConstants
import com.example.simplemovieapp.features.splash.presentation.viewModel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * SplashActivity
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@AndroidEntryPoint
class SplashActivity @Inject constructor() :
    BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override val viewModel: SplashViewModel by viewModels()

    override val binding: ActivitySplashBinding by viewBinding(ActivitySplashBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transparentStatusBarUI()
    }

    override fun setUp(extras: Bundle?) {
        super.setUp(extras)
        addViewModelsObservers()
    }

    private fun addViewModelsObservers() = with(viewModel) {
        observe(onNavigationEvent) { onNavigateToHome() }
    }

    private fun onNavigateToHome() {
        navigateDeferredTo {
            navigateTo<HomeActivity>()
            finishAffinity()
        }
    }

    private fun navigateDeferredTo(destination: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            destination()
        }, SplashConstants.DELAY_TIME_SPLASH_SCREEN)
    }
}