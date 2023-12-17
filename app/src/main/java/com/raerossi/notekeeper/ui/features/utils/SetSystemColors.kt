package com.raerossi.notekeeper.ui.features.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SetSystemColors(colorStatusBar: Color, colorNavigationBar: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(colorStatusBar)
        systemUiController.setNavigationBarColor(colorNavigationBar)
    }
}
