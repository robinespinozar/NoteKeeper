package com.raerossi.notekeeper.ui.features.task

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.raerossi.notekeeper.domain.Category
import com.raerossi.notekeeper.domain.Task
import com.raerossi.notekeeper.ui.features.components.BasicTextInputField
import com.raerossi.notekeeper.ui.features.components.DateSelector
import com.raerossi.notekeeper.ui.features.components.DropDownMenu
import com.raerossi.notekeeper.ui.features.components.GradientButton
import com.raerossi.notekeeper.ui.features.components.LoadingScreen
import com.raerossi.notekeeper.ui.features.components.TimeSelector
import com.raerossi.notekeeper.ui.features.components.TitleTopBar
import com.raerossi.notekeeper.ui.features.components.VerticalSpacer
import com.raerossi.notekeeper.ui.features.components.toItem
import com.raerossi.notekeeper.ui.theme.NoteKeeperTheme
import com.raerossi.notekeeper.ui.theme.generalSansFamily
import com.raerossi.notekeeper.ui.theme.primary50
import com.raerossi.notekeeper.ui.theme.primaryGradient
import com.raerossi.notekeeper.utils.extensions.toCalendar
import com.raerossi.notekeeper.utils.extensions.toLocalTime

@Composable
fun TaskScreen(
    taskViewModel: TaskViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onTaskHandlerClick: () -> Unit
) {
    val task by taskViewModel.task.observeAsState(Task())
    val uiState by taskViewModel.uiState.collectAsState()
    val categories by taskViewModel.listCategories.observeAsState(emptyList())

    TaskScreen(
        task = task,
        uiState = uiState,
        categories = categories,
        callBacks = TaskCallBacks(
            onBackClick = {},
            onTaskHandlerClick = {},
            onTaskChanged = { taskViewModel.onTaskChanged(it) }
        )
    )
}

@Composable
fun TaskScreen(
    task: Task,
    uiState: TaskUiState,
    categories: List<Category>,
    callBacks: TaskCallBacks
) {
    Scaffold(topBar = {
        TitleTopBar(
            title = "Add New Task",
            onBackClick = { callBacks.onBackClick() })
    }) {
        Column(modifier = Modifier.padding(it)) {
            if (uiState.isLoading) {
                LoadingScreen()
            } else {
                TaskContent(
                    task = task,
                    uiState = uiState,
                    categories = categories,
                    callBacks = callBacks
                )
            }
        }
    }
}

@Composable
fun TaskContent(
    task: Task,
    uiState: TaskUiState,
    categories: List<Category>,
    callBacks: TaskCallBacks
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
            .background(Color.White)
    ) {
        Title(
            title = task.title,
            isValidTitle = uiState.isValidTitle
        ) { callBacks.onTaskChanged(task.copy(title = it)) }
        VerticalSpacer(16)
        Description(description = task.description) { callBacks.onTaskChanged(task.copy(description = it)) }
        VerticalSpacer(16)
        Date(date = task.date) { callBacks.onTaskChanged(task.copy(date = it)) }
        VerticalSpacer(16)
        Time(
            startTime = task.startTime,
            endTime = task.endTime,
            onStartTimeChanged = { callBacks.onTaskChanged(task.copy(startTime = it)) },
            onEndTimeChanged = { callBacks.onTaskChanged(task.copy(endTime = it)) }
        )
        VerticalSpacer(16)
        Category(categories) { callBacks.onTaskChanged(task.copy(category = it)) }
        VerticalSpacer(8)
        AddNewCategory(Modifier.align(Alignment.End)) {}
        Spacer(modifier = Modifier.weight(1f))
        TaskHandlerButton { callBacks.onTaskHandlerClick(task.copy()) }
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
    isValidTitle: Boolean,
    onTextChanged: (String) -> Unit
) {
    BasicTextInputField(
        text = title,
        textLabel = "Title",
        textPlaceHolder = "Enter a title for the task",
        textSupport = if (!isValidTitle) "Enter a valid title" else null,
        isError = !isValidTitle,
        onTextChanged = { onTextChanged(it) }
    )
}

@Composable
private fun Description(
    description: String,
    onTextChanged: (String) -> Unit
) {
    BasicTextInputField(
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
    onDateChanged: (String) -> Unit
) {
    val dateState =
        rememberDatePickerState(initialSelectedDateMillis = date.toCalendar().timeInMillis)

    DateSelector(
        date = date,
        dateState = dateState,
        onDateChanged = { onDateChanged(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Time(
    startTime: String,
    endTime: String,
    onStartTimeChanged: (String) -> Unit,
    onEndTimeChanged: (String) -> Unit
) {
    val startLocalTime = startTime.toLocalTime()
    val endLocalTime = endTime.toLocalTime()

    val timeStartState = rememberTimePickerState(
        initialHour = startLocalTime.hour,
        initialMinute = startLocalTime.minute
    )
    val timeEndState = rememberTimePickerState(
        initialHour = endLocalTime.hour,
        initialMinute = endLocalTime.minute
    )

    Row {
        Column {
            Text(
                text = "Start Time",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary50
            )
            VerticalSpacer(8)
            TimeSelector(
                time = startTime,
                timeState = timeStartState,
                onTimeChanged = { onStartTimeChanged(it) }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column {
            Text(
                text = "End Time",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary50
            )
            VerticalSpacer(8)
            TimeSelector(
                time = endTime,
                timeState = timeEndState,
                onTimeChanged = { onEndTimeChanged(it) }
            )
        }
    }
}

@Composable
fun Category(categories: List<Category>, onItemSelected: (Int) -> Unit) {
    val items = categories.map { it.toItem() }

    DropDownMenu(
        textLabel = "Category",
        items = items,
        onItemSelected = { onItemSelected(it) }
    )
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
            TaskScreen(onBackClick = {}, onTaskHandlerClick = {})
        }
    }
}