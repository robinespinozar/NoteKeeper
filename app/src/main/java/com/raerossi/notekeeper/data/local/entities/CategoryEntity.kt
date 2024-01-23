package com.raerossi.notekeeper.data.local.entities

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raerossi.notekeeper.data.local.Tables
import com.raerossi.notekeeper.domain.Category

@Entity(tableName = Tables.CATEGORY)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "pending") val pending: Int,
    @ColumnInfo(name = "completed") val completed: Int,
    @ColumnInfo(name = "total") val total: Int,
    @ColumnInfo(name = "color1") val hexColor1: String,
    @ColumnInfo(name = "color2") val hexColor2: String
)

fun Category.toDataBase() = CategoryEntity(id, name, pending, completed, total, hexColor1, hexColor2 )