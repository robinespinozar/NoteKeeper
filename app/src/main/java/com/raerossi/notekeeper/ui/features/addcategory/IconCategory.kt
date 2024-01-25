package com.raerossi.notekeeper.ui.features.addcategory

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CategoryIndicator(
    brush: Brush,
    height: Dp = 30.dp,
    width: Dp = 30.dp,
    radius: Dp = 8.dp
) {
    Box(Modifier.fillMaxHeight()) {
        Canvas(
            Modifier
                .size(width, height)
                .align(Alignment.Center)
        ) {
            drawRoundRect(
                brush = brush,
                topLeft = Offset.Zero,
                size = Size(width.toPx(), height.toPx()),
                cornerRadius = CornerRadius(radius.toPx(), radius.toPx()),
            )
        }
    }
}