package com.example.material

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * CustomShapes
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@Immutable
data class CustomShapes(
    val roundedCorner8: Shape = RoundedCornerShape(size = 0.dp),
    val roundedCorner16: Shape = RoundedCornerShape(size = 0.dp)
)