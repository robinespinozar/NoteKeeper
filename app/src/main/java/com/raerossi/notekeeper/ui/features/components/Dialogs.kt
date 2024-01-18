package com.raerossi.notekeeper.ui.features.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.raerossi.notekeeper.ui.theme.neutralVariant30
import com.raerossi.notekeeper.ui.theme.primary40
import com.raerossi.notekeeper.ui.theme.title

@Composable
fun ErrorDialog(
    show: Boolean,
    title: String = "Advertisement",
    message: String = "An unexpected error has occurred. Try Again.",
    onDissmis: () -> Unit
) {
    if (show) {
        AlertDialog(
            title = { Text(text = title, style = MaterialTheme.typography.titleLarge) },
            text = { Text(text = message, style = MaterialTheme.typography.bodyMedium) },
            titleContentColor = MaterialTheme.colorScheme.title,
            textContentColor = MaterialTheme.colorScheme.neutralVariant30,
            containerColor = Color(0xFFFFFFFF),
            onDismissRequest = { onDissmis() },
            confirmButton = {
                TextButton(
                    onClick = { onDissmis() },
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary40)
                ) {
                    Text(
                        text = "Continue",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        )
    }
}