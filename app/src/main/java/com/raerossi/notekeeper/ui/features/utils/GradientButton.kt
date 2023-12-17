package com.raerossi.notekeeper.ui.features.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raerossi.notekeeper.ui.features.welcome.OnBoardingPage
import com.raerossi.notekeeper.ui.features.welcome.PagerScreen
import com.raerossi.notekeeper.ui.theme.NoteKeeperTheme
import com.raerossi.notekeeper.ui.theme.neutralVariant80
import com.raerossi.notekeeper.ui.theme.neutralVariant95
import com.raerossi.notekeeper.ui.theme.primaryGradient
import com.raerossi.notekeeper.ui.theme.secondaryGradient

@Composable
fun GradientButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    gradient: Brush,
    height: Int = 48,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .height(height.dp)
            .fillMaxWidth(),
        enabled = enabled,
        contentPadding = PaddingValues(),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = MaterialTheme.colorScheme.neutralVariant95
        )
    ) {
        Box(
            modifier = if (enabled) Modifier
                .fillMaxSize()
                .background(gradient)
                .then(modifier) else Modifier,
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                color = if (enabled) textColor else MaterialTheme.colorScheme.neutralVariant80,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonsScreenPreview() {
    NoteKeeperTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            GradientButton(
                text = "Log In",
                textColor = Color.White,
                gradient = MaterialTheme.colorScheme.primaryGradient
            ) {}
            VerticalSpacer(height = 16)
            GradientButton(
                text = "Start Now",
                textColor = Color(0xFF003A02),
                gradient = MaterialTheme.colorScheme.secondaryGradient
            ) {}
        }
    }
}
