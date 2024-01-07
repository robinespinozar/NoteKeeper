package com.raerossi.notekeeper.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.raerossi.notekeeper.ui.theme.NoteKeeperTheme

@Composable
fun HomeScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Home Screen",
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    NoteKeeperTheme {
        HomeScreen()
    }
}