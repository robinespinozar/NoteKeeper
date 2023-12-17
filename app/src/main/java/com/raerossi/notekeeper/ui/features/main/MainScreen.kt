package com.raerossi.notekeeper.ui.features.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.raerossi.notekeeper.ui.features.utils.SetSystemColors

@Composable
fun MainScreen() {
    SetSystemColors(colorStatusBar = Color(0xFFFFFFFF))
    Box(Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Main Screen"
        )
    }
}