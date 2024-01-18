package com.raerossi.notekeeper.ui.features.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.raerossi.notekeeper.ui.theme.primary30
import com.raerossi.notekeeper.ui.theme.primary50

@Composable
fun LinkButton(
    modifier: Modifier = Modifier,
    textDescription: String,
    textAction: String,
    descriptionColor: Color = MaterialTheme.colorScheme.primary30,
    actionColor: Color = MaterialTheme.colorScheme.primary50,
    height: Int = 48,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .height(height.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        onClick = { onClick() }
    ) {
        Row {
            Text(
                text = "$textDescription ",
                color = descriptionColor,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = textAction,
                color = actionColor,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}