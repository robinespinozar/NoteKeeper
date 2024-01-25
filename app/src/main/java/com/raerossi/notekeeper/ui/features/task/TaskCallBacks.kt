package com.raerossi.notekeeper.ui.features.task

import com.raerossi.notekeeper.domain.Task

data class TaskCallBacks(
    val onBackClick: () -> Unit,
    val onTaskHandlerClick: (Task) -> Unit,
    val onTaskChanged: (Task) -> Unit,
    val onErrorDialog: () -> Unit
)