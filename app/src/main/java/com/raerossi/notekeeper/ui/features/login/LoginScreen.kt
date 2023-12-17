package com.raerossi.notekeeper.ui.features.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun LoginScreen() {
    Box(Modifier.fillMaxSize().background(Color(0xFFFFB4AB))) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Login Screen"
        )
    }
}