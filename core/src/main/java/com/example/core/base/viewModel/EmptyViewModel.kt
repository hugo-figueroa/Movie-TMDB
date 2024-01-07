package com.example.core.base.viewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * EmptyViewModel - This ViewModel is for those screens that do not have any defined
 * functionality or do not need to generate logic.
 *
 * It is used to avoid Null ViewModels.
 *
 * @author (c) 2024, Hugo Figueroa
 */
@HiltViewModel
class EmptyViewModel @Inject constructor() : BaseViewModel()
