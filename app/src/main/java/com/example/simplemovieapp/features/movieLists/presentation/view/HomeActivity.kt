package com.example.simplemovieapp.features.movieLists.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import com.example.core.base.activity.BaseActivity
import com.example.core.base.viewModel.EmptyViewModel
import com.example.core.extensionFunctions.viewBinding
import com.example.simplemovieapp.R
import com.example.simplemovieapp.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity @Inject constructor() : BaseActivity<ActivityHomeBinding, EmptyViewModel>() {

    override val viewModel: EmptyViewModel by viewModels()

    override val binding: ActivityHomeBinding by viewBinding(ActivityHomeBinding::inflate)

    override fun setUp(extras: Bundle?) {
        super.setUp(extras)
        overridePendingTransition(R.anim.enter_from_right, R.anim.none)
    }



}