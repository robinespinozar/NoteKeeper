package com.raerossi.notekeeper.ui.features.task

import com.raerossi.notekeeper.domain.Category

data class TaskUiState(
    val isLoading: Boolean = false,
    val isValidTitle: Boolean = true,
    val isValidTime: Boolean = true,
    val isValidCategory: Boolean = true
) {
    fun validateTask() = isValidTitle && isValidTime && isValidCategory
}