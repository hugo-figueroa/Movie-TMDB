package com.example.core.extensionFunctions

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.annotation.AnyRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.core.models.NavigationEvent

/**
 * FragmentEF
 *
 * @author (c) 2024, Hugo Figueroa
 */

fun Fragment.taskBeforeBack(unit: () -> Unit) {
    activity?.onBackPressedDispatcher?.addCallback(
        viewLifecycleOwner,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                unit.invoke()
            }
        })
}

inline fun <reified T : Activity> Fragment.navigateTo(noinline init: Intent.() -> Unit = {}) {
    val intent = Intent(activity, T::class.java)
    intent.init()
    startActivity(intent)
}

fun Fragment.navigateTo(navigationEvent: NavigationEvent, handleException: Boolean = true) {
    navigateTo(
        navigationEvent.destinationId,
        navigationEvent.arguments,
        handleException,
        navigationEvent.navOption
    )
}

@SuppressWarnings("TooGenericExceptionCaught")
fun Fragment.navigateTo(
    @AnyRes destination: Int,
    arguments: Bundle? = null,
    handleException: Boolean = false,
    navOptions: NavOptions? = null
) {
    try {
        findNavController().navigate(destination, arguments, navOptions)
    } catch (e: Exception) {
        if (handleException) {
            logError("")
        } else {
            throw e
        }
    }
}

fun Fragment.navigateUp() {
    findNavController().navigateUp()
}