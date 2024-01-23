package com.raerossi.notekeeper.utils.extensions

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar

fun Long.toLocalDate(): LocalDate {
    return Instant.ofEpochMilli(this).atZone(ZoneId.of("UTC")).toLocalDate()
}

fun LocalDate.customFormat(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return formatter.format(this)
}

fun String.toCalendar(): Calendar {
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    val date = formatter.parse(this)
    val calendar = Calendar.getInstance()
    calendar.time = date

    return calendar
}

fun String.toLocalTime(): LocalTime {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return LocalTime.parse(this, formatter)
}