package com.example.core.extensionFunctions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowInsetsController

/**
 * ActivityEF
 *
 * @author (c) 2024, Hugo Figueroa
 */
inline fun <reified T : Activity> Activity.navigateTo(noinline init: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
}

@SuppressLint("WrongConstant")
fun Activity.transparentStatusBarUI() {
    window.statusBarColor = Color.TRANSPARENT
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.let { _windowInsetsController ->
            _windowInsetsController.systemBarsBehavior =
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            window.setDecorFitsSystemWindows(false)
            window.navigationBarColor = Color.TRANSPARENT
            /**
             * if u need hide statusBar and not show any icons use
             * _windowInsetsController.hide(WindowInsets.Type.systemBars())
             */
        }
    } else {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}
