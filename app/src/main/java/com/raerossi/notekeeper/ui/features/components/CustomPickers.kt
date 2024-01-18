package com.raerossi.notekeeper.ui.features.components

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.raerossi.notekeeper.ui.theme.neutralVariant40
import com.raerossi.notekeeper.ui.theme.primary20
import com.raerossi.notekeeper.ui.theme.primary40
import com.raerossi.notekeeper.ui.theme.primary40_alpha40
import com.raerossi.notekeeper.ui.theme.primary70
import com.raerossi.notekeeper.ui.theme.surfaceContainerHigh
import com.raerossi.notekeeper.ui.theme.surfaceContainerHighest
import com.raerossi.notekeeper.ui.theme.title
import com.raerossi.notekeeper.utils.extensions.convertFormat24To12
import com.raerossi.notekeeper.utils.extensions.getAbbreviatedMonth
import com.raerossi.notekeeper.utils.extensions.getFormat12Hours
import com.raerossi.notekeeper.utils.extensions.toLocalDate
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.Month
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Date
import java.util.Locale

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelector(
    dateState: DatePickerState,
    onTextChanged: (String) -> Unit
) {
    val currentDate = getCurrentDate()
    var showDialog by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableLongStateOf(currentDate.timeInMillis) }

    BasicInputField(
        modifier = Modifier.clickable { showDialog = true },
        text = getDisplayDate(selectedDate),
        textLabel = "Date",
        textPlaceHolder = "Enter the date",
        enabled = false,
        readOnly = true,
        isPicker = true,
        onTextChanged = { onTextChanged(it) },
        trailingIcon = {
            IconButton(onClick = { showDialog = true }
            ) {
                Icon(
                    imageVector = Icons.Rounded.DateRange,
                    tint = MaterialTheme.colorScheme.primary40_alpha40,
                    contentDescription = "date"
                )
            }
        }
    )

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            colors = DatePickerDefaults.colors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
            ),
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary70)
                ) {
                    Text(
                        text = "Cancel",
                        color = MaterialTheme.colorScheme.primary70,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        selectedDate = dateState.selectedDateMillis!!
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary70)
                ) {
                    Text(
                        text = "Ok",
                        color = MaterialTheme.colorScheme.primary70,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        ) {
            DatePicker(
                state = dateState,
                showModeToggle = false,
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                    selectedDayContainerColor = MaterialTheme.colorScheme.primary70,
                    selectedYearContainerColor = MaterialTheme.colorScheme.primary70,
                    headlineContentColor = MaterialTheme.colorScheme.title,
                    currentYearContentColor = MaterialTheme.colorScheme.primary70,
                    dayContentColor = MaterialTheme.colorScheme.neutralVariant40,
                    yearContentColor = MaterialTheme.colorScheme.neutralVariant40,
                    todayContentColor = MaterialTheme.colorScheme.primary70,
                    todayDateBorderColor = MaterialTheme.colorScheme.primary70
                )
            )
        }
    }
}

fun getCurrentDate(): Calendar {
    val currentDate = LocalDate.now()
    val calendar = Calendar.getInstance()
    calendar.set(currentDate.year, currentDate.monthValue - 1, currentDate.dayOfMonth)
    return calendar
}

fun getDisplayDate(selectedDate: Long): String {
    val localDate = selectedDate.toLocalDate()
    val dayOfWeek = localDate.dayOfWeek.toString().lowercase().replaceFirstChar { it.uppercase() }
    val day = localDate.dayOfMonth
    val month = getAbbreviatedMonth(localDate.month.toString())
    val year = localDate.year

    return "$dayOfWeek, $month $day $year"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeSelector(
    timeState: TimePickerState,
    onTextChanged: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf(getFormat12Hours(timeState)) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        BasicInputField(
            modifier = Modifier.clickable { showDialog = true },
            text = selectedTime.first,
            width = 125,
            enabled = false,
            readOnly = true,
            isPicker = true,
            textPlaceHolder = "start",
            onTextChanged = { onTextChanged(it) },
            trailingIcon = {
                IconButton(onClick = { showDialog = true }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.AccessTime,
                        tint = MaterialTheme.colorScheme.primary40_alpha40,
                        contentDescription = "date"
                    )
                }
            }
        )
        HorizontalSpacer(12)
        Text(
            text = selectedTime.second,
            style = MaterialTheme.typography.labelMedium,
            color = Color(0xFF000000)
        )
    }

    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog = false }
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
            ) {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TimePicker(
                        state = timeState,
                        colors = TimePickerDefaults.colors(
                            clockDialColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                            selectorColor = MaterialTheme.colorScheme.primary70,
                            periodSelectorBorderColor = MaterialTheme.colorScheme.primary70,
                            periodSelectorSelectedContentColor = MaterialTheme.colorScheme.primary20,
                            periodSelectorSelectedContainerColor = MaterialTheme.colorScheme.primary70,
                            timeSelectorSelectedContainerColor = MaterialTheme.colorScheme.primary70,
                            timeSelectorSelectedContentColor = MaterialTheme.colorScheme.primary20,
                            timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                        )
                    )
                    Row(
                        Modifier
                            .align(Alignment.End)
                            .padding(horizontal = 8.dp)
                    ) {
                        TextButton(
                            onClick = { showDialog = false },
                            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary70)
                        ) {
                            Text(
                                text = "Cancel",
                                color = MaterialTheme.colorScheme.primary70,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        TextButton(
                            onClick = {
                                showDialog = false
                                selectedTime = getFormat12Hours(timeState)
                            },
                            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary70)
                        ) {
                            Text(
                                text = "Ok",
                                color = MaterialTheme.colorScheme.primary70,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}