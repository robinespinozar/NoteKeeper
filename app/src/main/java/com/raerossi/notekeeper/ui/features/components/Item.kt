package com.raerossi.notekeeper.ui.features.components

import com.raerossi.notekeeper.domain.Category


data class Item(val id: Int, val name: String)

fun Category.toItem() = Item(this.id, this.name)