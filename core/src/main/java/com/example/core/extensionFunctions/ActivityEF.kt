package com.example.core.extensionFunctions

import android.app.Activity
import android.content.Intent

/**
 * ActivityEF
 *
 * @author (c) 2023, Hugo Figueroa
 */
inline fun <reified T : Activity> Activity.navigateTo(noinline init: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
}
