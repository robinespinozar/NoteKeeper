package com.raerossi.notekeeper.domain

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import com.raerossi.notekeeper.data.local.entities.CategoryEntity

data class Category(
    val id: Int = 0,
    val name: String,
    val pending: Int,
    val completed: Int,
    val total: Int,
    val hexColor1: String,
    val hexColor2: String
) {
    fun getCategoryBrush(): Brush {
        val color1 = Color(hexColor1.toColorInt())
        val color2 = Color(hexColor2.toColorInt())
        return Brush.horizontalGradient(colors = listOf(color1, color2))
    }
}

fun CategoryEntity.toDomain() = Category(id, name, pending, completed, total, hexColor1, hexColor2)