package com.raerossi.notekeeper.domain.usecases

import com.raerossi.notekeeper.data.TaskRepository
import com.raerossi.notekeeper.domain.Task
import javax.inject.Inject

class CreateTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) = repository.insertTask(task)
}