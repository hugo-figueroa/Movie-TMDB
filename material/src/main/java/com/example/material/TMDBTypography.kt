package com.example.material

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * TMDBTypography
 *
 * @author (c) 2024, Hugo Figueroa
 *
 * Set of Material typography styles to start with
 * */

private val LineHeightTitleLarge = 32.sp
private val LineHeightBody = 16.sp

val typography = TMDBTypography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = LineHeightBody
    ),
    title1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = LineHeightTitleLarge
    )
)

@Immutable
data class TMDBTypography(
    val body1: TextStyle,
    val title1: TextStyle
)