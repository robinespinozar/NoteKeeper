package com.raerossi.notekeeper.ui.features.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raerossi.notekeeper.ui.theme.NoteKeeperTheme
import com.raerossi.notekeeper.ui.theme.description
import com.raerossi.notekeeper.ui.theme.title

object Sizes {
    const val sizeBig = 0
    const val sizeMed = 1
}

@Composable
fun TitleAndDescription(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    spacing: Int = 8,
    size: Int = Sizes.sizeBig,
    centerAlign: Boolean = false,
    darkMode: Boolean = false
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        val alignment = if (centerAlign) TextAlign.Center else TextAlign.Left

        TitleText(
            text = title,
            alignment = alignment,
            darkMode = darkMode,
            size = size
        )
        VerticalSpacer(spacing)
        DescriptionText(
            text = description,
            alignment = alignment,
            darkMode = darkMode,
            size = size
        )
    }
}

@Composable
fun TitleText(
    text: String,
    alignment: TextAlign,
    darkMode: Boolean,
    size: Int
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        textAlign = alignment,
        color = if (darkMode) Color.White else MaterialTheme.colorScheme.title,
        style = if (size == Sizes.sizeBig) MaterialTheme.typography.displaySmall else MaterialTheme.typography.headlineMedium
    )
}

@Composable
fun DescriptionText(
    text: String,
    alignment: TextAlign,
    darkMode: Boolean,
    size: Int
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        textAlign = alignment,
        color = if (darkMode) Color.White else MaterialTheme.colorScheme.description,
        style = if (size == Sizes.sizeBig) MaterialTheme.typography.bodySmall else MaterialTheme.typography.bodyMedium
    )
}

@Preview(showBackground = true)
@Composable
fun MyTitlesPreviews() {
    NoteKeeperTheme {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            TitleAndDescription(
                title = "Confirm your email",
                description = "We just sent you an email to\n" + "office@designmesocial.com"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(Modifier.background(Color.Gray)) {
                TitleAndDescription(
                    title = "Confirm your email",
                    darkMode = true,
                    description = "We just sent you an email to\n" + "office@designmesocial.com"
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            TitleAndDescription(
                title = "Confirm your email",
                centerAlign = true,
                description = "We just sent you an email to\n" + "office@designmesocial.com"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(Modifier.background(Color.Gray)) {
                TitleAndDescription(
                    title = "Confirm your email",
                    darkMode = true,
                    centerAlign = true,
                    description = "We just sent you an email to\n" + "office@designmesocial.com"
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            TitleAndDescription(
                title = "Login",
                description = "Enter the email address you’d like to use to sign in to SmartBank.",
                size = Sizes.sizeMed
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(Modifier.background(Color.Gray)) {
                TitleAndDescription(
                    title = "Login",
                    darkMode = true,
                    description = "Enter the email address you’d like to use to sign in to SmartBank.",
                    size = Sizes.sizeMed
                )
            }
        }
    }
}