package com.raerossi.notekeeper.utils.extensions

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

fun getAbbreviatedMonth(month: String): String {
    val month = Month.valueOf(month)
    return month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
}

@OptIn(ExperimentalMaterial3Api::class)
fun getFormat12Hours(timeState: TimePickerState): Pair<String, String> {
    val hour = if (timeState.hour <= 9) "0${timeState.hour}" else "${timeState.hour}"
    val minute = if (timeState.minute <= 9) "0${timeState.minute}" else "${timeState.minute}"
    val hour24 = "$hour:$minute"

    return convertFormat24To12(hour24)
}

fun convertFormat24To12(hour24: String): Pair<String, String> {
    val formatter24Hours = DateTimeFormatter.ofPattern("HH:mm")
    val formatter12Hours = DateTimeFormatter.ofPattern("hh:mm")

    val time24 = LocalTime.parse(hour24, formatter24Hours)
    val time12 = time24.format(formatter12Hours)

    val dayPeriod = if (time24[java.time.temporal.ChronoField.AMPM_OF_DAY] == 0) "AM" else "PM"
    return Pair(time12, dayPeriod)
}

fun Long.toLocalDate(): LocalDate {
   return Instant.ofEpochMilli(this).atZone(ZoneId.of("UTC")).toLocalDate()
}