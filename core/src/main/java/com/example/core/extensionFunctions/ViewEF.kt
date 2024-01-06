package com.example.core.extensionFunctions

import android.view.View
import com.example.core.utils.SafeClickListener

/**
 * ViewEF
 *
 * @author (c) 2024, Hugo Figueroa
 * */
fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}