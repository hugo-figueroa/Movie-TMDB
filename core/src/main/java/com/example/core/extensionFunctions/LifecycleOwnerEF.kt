package com.example.core.extensionFunctions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * LifecycleOwnerEF
 *
 * @author (c) 2024, Hugo Figueroa
 */
fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) {
    liveData.observe(this, Observer(body))
}