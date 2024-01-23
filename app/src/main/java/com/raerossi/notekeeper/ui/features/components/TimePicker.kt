package com.raerossi.notekeeper.ui.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.raerossi.notekeeper.ui.theme.primary20
import com.raerossi.notekeeper.ui.theme.primary40_alpha40
import com.raerossi.notekeeper.ui.theme.primary70
import com.raerossi.notekeeper.ui.theme.surfaceContainerHigh
import com.raerossi.notekeeper.ui.theme.surfaceContainerHighest
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeSelector(
    time: String,
    timeState: TimePickerState,
    onTimeChanged: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    val timeAmPm = convertFormat24To12(time)

    Row(verticalAlignment = Alignment.CenterVertically) {
        BasicInputField(
            modifier = Modifier.clickable { showDialog = true },
            text = timeAmPm.first,
            width = 125,
            enabled = false,
            readOnly = true,
            isPicker = true,
            textPlaceHolder = "start",
            onTextChanged = { },
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
            text = timeAmPm.second,
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
                                onTimeChanged(getFormat24Hours(timeState))
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

fun convertFormat24To12(hour24: String): Pair<String, String> {
    val formatter24Hours = DateTimeFormatter.ofPattern("HH:mm")
    val formatter12Hours = DateTimeFormatter.ofPattern("hh:mm")

    val time24 = LocalTime.parse(hour24, formatter24Hours)
    val time12 = time24.format(formatter12Hours)

    val dayPeriod = if (time24[java.time.temporal.ChronoField.AMPM_OF_DAY] == 0) "AM" else "PM"
    return Pair(time12, dayPeriod)
}

@OptIn(ExperimentalMaterial3Api::class)
fun getFormat24Hours(timeState: TimePickerState): String {
    val hour = if (timeState.hour <= 9) "0${timeState.hour}" else "${timeState.hour}"
    val minute = if (timeState.minute <= 9) "0${timeState.minute}" else "${timeState.minute}"
    return "$hour:$minute"
}
