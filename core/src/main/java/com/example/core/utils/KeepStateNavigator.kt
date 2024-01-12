package com.example.core.utils

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator

/**
 * KeepStateNavigator
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@Navigator.Name("KeepStateFragment") // `keep_state_fragment` is used in navigation xml
class KeepStateNavigator(
    private val context: Context,
    private val manager: FragmentManager, // Should pass childFragmentManager.
    private val containerId: Int
) : FragmentNavigator(context, manager, containerId) {

    private var isInitialNavigation = true

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination? {
        val tag = destination.id.toString()
        val transaction = manager.beginTransaction()

        manager.executePendingTransactions()

        val currentFragment = manager.primaryNavigationFragment
        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }

        var fragment = manager.findFragmentByTag(tag)
        if (fragment == null) { //Destination already exist?
            val className = destination.className
            fragment = manager.fragmentFactory.instantiate(context.classLoader, className)
            fragment.arguments = args
            if (isInitialNavigation) {
                transaction.replace(containerId, fragment, tag)
            } else {
                transaction.add(containerId, fragment, tag)
            }
        } else {
            transaction.show(fragment)
        }

        transaction.setPrimaryNavigationFragment(fragment)
        transaction.setReorderingAllowed(true)
        transaction.commit()

        isInitialNavigation = false

        return destination
    }
}