package com.example.core.models

import android.os.Bundle
import androidx.annotation.AnyRes
import androidx.navigation.NavOptions

/**
 * Class to wrap a navigation action with its respective arguments.
 * Used in ViewModels to handle the navigation logic by publishing a NavigationEvent
 * to the view.
 *
 */
data class NavigationEvent(
    @AnyRes val destinationId: Int,
    val arguments: Bundle? = null,
    val navOption: NavOptions? = null
)