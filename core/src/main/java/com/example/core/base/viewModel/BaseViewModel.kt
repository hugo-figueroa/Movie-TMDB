package com.example.core.base.viewModel

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.core.utils.SingleLiveEvent
import com.example.core.models.NavigationEvent

/**
 * BaseViewModel
 *
 * @author (c) 2024, Hugo Figueroa
 * */
abstract class BaseViewModel: ViewModel() {

    val onNavigationEventMLD: SingleLiveEvent<NavigationEvent> = SingleLiveEvent()
    val onNavigationEvent get(): LiveData<NavigationEvent> = onNavigationEventMLD

    @CallSuper
    open fun setUp(bundle: Bundle?) {}
}