package com.example.core.extensionFunctions

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * ConnectivityEF
 *
 * @author (c) 2023, Hugo Figueroa
 * */
@SuppressLint("MissingPermission", "NewApi")
fun ConnectivityManager.isNetworkAvailable(): Boolean {
    return getNetworkCapabilities(activeNetwork)?.run {
        when {
            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    } ?: false
}