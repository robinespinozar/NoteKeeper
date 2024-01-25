package com.raerossi.notekeeper.domain

import com.raerossi.notekeeper.utils.extensions.customFormat
import java.time.LocalDate

data class Task(
    val title: String = "",
    val description: String = "",
    val date: String = LocalDate.now().customFormat(),
    val startTime: String = "08:00",
    val endTime: String = "09:00",
    val category: Int = 1
)