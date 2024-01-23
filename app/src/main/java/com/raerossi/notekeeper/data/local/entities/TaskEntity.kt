package com.raerossi.notekeeper.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raerossi.notekeeper.data.local.Tables
import com.raerossi.notekeeper.domain.getCurrentDate
import com.raerossi.notekeeper.utils.extensions.customFormat
import java.time.LocalDate

@Entity(tableName = Tables.TASK)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "date") val date: String = LocalDate.now().customFormat(),
    @ColumnInfo(name = "startTime") val startTime: String = "08:00",
    @ColumnInfo(name = "endTime") val endTime: String = "09:00",
    @ColumnInfo(name = "category") val category: Int = 0
)