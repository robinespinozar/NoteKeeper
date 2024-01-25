package com.raerossi.notekeeper.ui.features.components

import androidx.compose.ui.graphics.Brush
import com.raerossi.notekeeper.domain.Category

data class Item(
    val id: Int,
    val name: String,
    val brush: Brush
)

fun Category.toItem() = Item(
    id = this.id,
    name = this.name,
    brush = this.getBrush()
)