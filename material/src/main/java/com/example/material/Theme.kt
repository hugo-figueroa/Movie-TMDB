package com.example.material

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

/**
 * Theme
 *
 * @author (c) 2024, Hugo Figueroa
 * */

private val Grid0 = 0.dp
private val Grid4 = 4.dp
private val Grid8 = 8.dp
private val Grid12 = 12.dp
private val Grid16 = 16.dp
private val Grid20 = 20.dp
private val Grid24 = 24.dp
private val Grid28 = 28.dp
private val Grid32 = 32.dp
private val Grid36 = 36.dp
private val Grid40 = 40.dp
private val Grid44 = 44.dp
private val Grid48 = 48.dp
private val Grid52 = 52.dp
private val Grid56 = 56.dp
private val Grid60 = 60.dp
private val Grid64 = 64.dp

private val RoundedCorner8 = 8.dp

private val LocalTMDBColors = staticCompositionLocalOf<TMDBColors> {
    error("No Colors provided")
}
private val LocalTMDBShape = staticCompositionLocalOf { shapes }

private val LocalTMDBGrids = staticCompositionLocalOf { Grid() }

private val LocalTMDBTypography = staticCompositionLocalOf {
    TMDBTypography(
        body1 = TextStyle.Default,
        title1 = TextStyle.Default
    )
}

private val LocalTMDBCustomShapes = staticCompositionLocalOf { CustomShapes() }

private val lightColors = TMDBColors(
    primary = orange500,
    primaryVariant = orange700,
    secondary = gray800,
    surfaceLight = gray100
)

private val grids = Grid(
    grid0 = Grid0,
    grid05 = Grid4,
    grid1 = Grid8,
    grid15 = Grid12,
    grid2 = Grid16,
    grid25 = Grid20,
    grid3 = Grid24,
    grid35 = Grid28,
    grid4 = Grid32,
    grid45 = Grid36,
    grid5 = Grid40,
    grid55 = Grid44,
    grid6 = Grid48,
    grid65 = Grid52,
    grid7 = Grid56,
    grid75 = Grid60,
    grid8 = Grid64
)

val customShapes = CustomShapes(
    roundedCorner8 = RoundedCornerShape(
        topStart = RoundedCorner8,
        topEnd = RoundedCorner8,
        bottomStart = RoundedCorner8,
        bottomEnd = RoundedCorner8
    )
)

@Composable
fun MovieTHDBTheme(content: @Composable() () -> Unit) {
    CompositionLocalProvider(
        LocalTMDBColors provides lightColors,
        LocalTMDBShape provides shapes,
        LocalTMDBGrids provides grids,
        LocalTMDBTypography provides typography,
        LocalTMDBCustomShapes provides customShapes,
        content = content
    )
}

object TMDBTheme {
    val colors: TMDBColors
        @Composable
        get() = LocalTMDBColors.current
    val shapes: Shapes
        @Composable
        get() = LocalTMDBShape.current
    val customShapes: CustomShapes
        @Composable
        get() = LocalTMDBCustomShapes.current
    val grids: Grid
        @Composable
        get() = LocalTMDBGrids.current
    val typography: TMDBTypography
        @Composable
        get() = LocalTMDBTypography.current
}