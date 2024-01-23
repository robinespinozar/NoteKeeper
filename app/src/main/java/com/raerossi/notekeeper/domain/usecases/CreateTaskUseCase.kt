package com.raerossi.notekeeper.domain.usecases

import com.raerossi.notekeeper.data.TaskRepository
import javax.inject.Inject

class CreateTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

}