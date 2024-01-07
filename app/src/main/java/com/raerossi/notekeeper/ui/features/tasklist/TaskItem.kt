package com.raerossi.notekeeper.ui.features.tasklist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.raerossi.notekeeper.domain.Task
import com.raerossi.notekeeper.ui.features.utils.HorizontalSpacer
import com.raerossi.notekeeper.ui.features.utils.VerticalSpacer
import com.raerossi.notekeeper.ui.theme.NoteKeeperTheme
import com.raerossi.notekeeper.ui.theme.neutralVariant40
import com.raerossi.notekeeper.ui.theme.seed
import com.raerossi.notekeeper.ui.theme.title

@Composable
fun TaskItem(task: Task) {
    Card(
        Modifier
            .height(64.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        CardContent(task = task)
    }
}

@Composable
private fun CardContent(modifier: Modifier = Modifier, task: Task) {
    Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        HorizontalSpacer(16)
        TaskCategoryIndicator()
        HorizontalSpacer(10)
        TaskInformation()
        Spacer(modifier = Modifier.weight(1f))
        TaskCheckBox()
    }
}

@Composable
fun TaskInformation() {
    Column {
        Text(
            text = "Clean Room",
            color = MaterialTheme.colorScheme.title,
            style = MaterialTheme.typography.bodyMedium
        )
        VerticalSpacer(4)
        Text(
            text = "09:00 - 11:00 a.m",
            color = MaterialTheme.colorScheme.neutralVariant40,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun TaskCheckBox() {
    val isTaskCompleted by remember { mutableStateOf(false) }

    Checkbox(
        modifier = Modifier.padding(end = 16.dp),
        checked = isTaskCompleted,
        onCheckedChange = { !isTaskCompleted },
        colors = CheckboxDefaults.colors(
            checkedColor = MaterialTheme.colorScheme.seed,
            uncheckedColor = Color(0xFF49454E)
        )
    )
}

@Composable
fun TaskCategoryIndicator(
    color: Color = Color(0xFFB1F2B2),
    height: Dp = 32.dp,
    width: Dp = 2.5.dp,
    radius: Dp = 2.5.dp
) {
    Box(Modifier.fillMaxHeight()) {
        Canvas(
            Modifier
                .size(width, height)
                .align(Alignment.Center)
        ) {
            drawRoundRect(
                color = color,
                topLeft = Offset.Zero,
                size = Size(width.toPx(), height.toPx()),
                cornerRadius = CornerRadius(radius.toPx(), radius.toPx()),
            )
        }
    }
}