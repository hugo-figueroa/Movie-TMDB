package com.example.material

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * TMDBColors
 *
 * @author (c) 2024, Hugo Figueroa
 * */
val orange200 = Color(0xFFFFCDD2)
val orange500 = Color(0xFFEF9A9A)
val orange700 = Color(0xFFEF5350)
val gray800 = Color(0xFF424242)
val gray100 = Color(0xFFF5F5F5)
val gray400 = Color(0xFFBDBDBD)
val yellow700 = Color(0xFFFBC02D)
val whiteNeutral = Color(0xFFFFFFFF)
@Immutable
data class TMDBColors(
    val primary: Color,
    val primaryVariant: Color,
    val secondary: Color,
    val surfaceLight: Color,
    val primarySurface: Color,
    val orangeLight: Color,
    val grayLight: Color,
    val yellowPrimary: Color
)