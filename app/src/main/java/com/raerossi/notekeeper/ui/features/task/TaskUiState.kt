package com.raerossi.notekeeper.ui.features.task

import com.raerossi.notekeeper.domain.Category

data class TaskUiState(
    val isLoading: Boolean = false,
    val isValidTitle: Boolean = true,
    val isValidTime: Boolean = true,
    val showErrorDialog: Boolean = false
) {
    fun validateTask() = isValidTitle && isValidTime
}