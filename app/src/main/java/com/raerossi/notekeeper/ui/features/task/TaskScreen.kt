package com.raerossi.notekeeper.ui.features.task

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raerossi.notekeeper.ui.features.components.DateSelector
import com.raerossi.notekeeper.ui.features.components.DropDownMenu
import com.raerossi.notekeeper.ui.features.components.GradientButton
import com.raerossi.notekeeper.ui.features.components.HorizontalSpacer
import com.raerossi.notekeeper.ui.features.components.TextExposedInputField
import com.raerossi.notekeeper.ui.features.components.TimeSelector
import com.raerossi.notekeeper.ui.features.components.TitleTopBar
import com.raerossi.notekeeper.ui.features.components.VerticalSpacer
import com.raerossi.notekeeper.ui.theme.NoteKeeperTheme
import com.raerossi.notekeeper.ui.theme.generalSansFamily
import com.raerossi.notekeeper.ui.theme.neutralVariant30
import com.raerossi.notekeeper.ui.theme.neutralVariant40
import com.raerossi.notekeeper.ui.theme.neutralVariant80
import com.raerossi.notekeeper.ui.theme.primary40
import com.raerossi.notekeeper.ui.theme.primary50
import com.raerossi.notekeeper.ui.theme.primary70
import com.raerossi.notekeeper.ui.theme.primaryGradient
import com.raerossi.notekeeper.ui.theme.seed
import com.raerossi.notekeeper.ui.theme.surfaceContainerHigh
import com.raerossi.notekeeper.ui.theme.title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen() {
    Scaffold(topBar = { TitleTopBar(title = "Add New Task", onBackClick = {}) }) {
        Column(modifier = Modifier.padding(it)) {
            TaskContent()
        }
    }
}

@Composable
fun TaskContent() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
            .background(Color.White)
    ) {
        Title("") {}
        VerticalSpacer(16)
        Description("") {}
        VerticalSpacer(16)
        Date("") {}
        VerticalSpacer(16)
        Time("", "") {}
        VerticalSpacer(16)
        Category()
        VerticalSpacer(8)
        AddNewCategory(Modifier.align(Alignment.End)) {}
        Spacer(modifier = Modifier.weight(1f))
        TaskHandlerButton() {}
    }
}

@Composable
fun TaskHandlerButton(onClick: () -> Unit) {
    GradientButton(
        modifier = Modifier.padding(bottom = 16.dp),
        text = "Create Task",
        textColor = Color.White,
        gradient = MaterialTheme.colorScheme.primaryGradient,
        onClick = { onClick() }
    )
}

@Composable
private fun Title(
    title: String,
    onTextChanged: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    TextExposedInputField(
        text = text,
        textLabel = "Title",
        textPlaceHolder = "Enter a title for the task",
        onTextChanged = { text = it /*onTextChanged(it)*/ }
    )
}

@Composable
private fun Description(
    description: String,
    onTextChanged: (String) -> Unit
) {
    TextExposedInputField(
        text = description,
        textLabel = "Description",
        textPlaceHolder = "Enter a description for the task",
        height = 68,
        maxLines = 2,
        onTextChanged = { onTextChanged(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Date(
    date: String,
    onTextChanged: (String) -> Unit
) {
    val dateState = rememberDatePickerState()

    DateSelector(dateState) {}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Time(
    startTime: String,
    endTime: String,
    onTextChanged: (String) -> Unit
) {
    val timeStartState = rememberTimePickerState(initialHour = 8, initialMinute = 0)
    val timeEndState = rememberTimePickerState(initialHour = 9, initialMinute = 0)

    Row {
        Column {
            Text(
                text = "Start Time",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary50
            )
            VerticalSpacer(8)
            TimeSelector(timeState = timeStartState){}
        }
        Spacer(modifier = Modifier.weight(1f))
        Column {
            Text(
                text = "End Time",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary50
            )
            VerticalSpacer(8)
            TimeSelector(timeState = timeEndState){}
        }
    }
}

@Composable
fun Category() {
    val categories = listOf("Work", "Gym", "Homework", "University")
    DropDownMenu(textLabel = "Category", items = categories)
}

@Composable
private fun AddNewCategory(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Text(
        text = "Add New Category",
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = generalSansFamily,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
        color = Color(0xFF006E08),
        modifier = modifier.clickable { onClick() }
    )
}


@Preview(showBackground = true)
@Composable
fun TaskScreenPreview() {
    NoteKeeperTheme {
        Box(Modifier.fillMaxSize()) {
            TaskScreen()
        }
    }
}