package com.example.material

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * TMDBColors
 *
 * @author (c) 2024, Hugo Figueroa
 * */
val orange200 = Color(0xFFFFAB91)
val orange500 = Color(0xFFFF5722)
val orange700 = Color(0xFFE64A19)
val gray800 = Color(0xFF424242)
val gray100 = Color(color = 0xFFF5F5F5)
@Immutable
data class TMDBColors(
    val primary: Color,
    val primaryVariant: Color,
    val secondary: Color,
    val surfaceLight: Color
)