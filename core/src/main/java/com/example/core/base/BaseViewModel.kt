package com.example.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.core.utils.SingleLiveEvent
import com.example.core.models.NavigationEvent

/**
 * BaseViewModel
 *
 * @author (c) 2024,
 * */
abstract class BaseViewModel: ViewModel() {

    val onNavigationEventMLD: SingleLiveEvent<NavigationEvent> = SingleLiveEvent()
    val onNavigationEvent get(): LiveData<NavigationEvent> = onNavigationEventMLD

}