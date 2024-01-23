package com.raerossi.notekeeper.ui.features.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.raerossi.notekeeper.ui.theme.neutralVariant40
import com.raerossi.notekeeper.ui.theme.primary40_alpha40
import com.raerossi.notekeeper.ui.theme.primary70
import com.raerossi.notekeeper.ui.theme.surfaceContainerHigh
import com.raerossi.notekeeper.ui.theme.title
import com.raerossi.notekeeper.utils.extensions.customFormat
import com.raerossi.notekeeper.utils.extensions.toCalendar
import com.raerossi.notekeeper.utils.extensions.toLocalDate
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelector(
    date: String,
    dateState: DatePickerState,
    onDateChanged: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    BasicInputField(
        modifier = Modifier.clickable { showDialog = true },
        text = getDisplayDate(date),
        textLabel = "Date",
        textPlaceHolder = "Enter the date",
        enabled = false,
        readOnly = true,
        isPicker = true,
        onTextChanged = { },
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
                        onDateChanged(dateState.selectedDateMillis!!.toLocalDate().customFormat())
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

fun getDisplayDate(selectedDate: String): String {
    val localDate = selectedDate.toCalendar().timeInMillis.toLocalDate()
    val dayOfWeek = localDate.dayOfWeek.toString().lowercase().replaceFirstChar { it.uppercase() }
    val day = localDate.dayOfMonth
    val month = getAbbreviatedMonth(localDate.month.toString())
    val year = localDate.year

    return "$dayOfWeek, $month $day $year"
}

fun getAbbreviatedMonth(month: String): String {
    val month = Month.valueOf(month)
    return month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
}
