package com.raerossi.notekeeper.ui.features.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RegistrationScreen() {
    Box(Modifier.fillMaxSize().background(Color(0xFF41C43A))) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Registration Screen"
        )
    }
}