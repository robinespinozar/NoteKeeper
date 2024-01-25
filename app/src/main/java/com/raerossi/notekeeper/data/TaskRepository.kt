package com.raerossi.notekeeper.data

import com.raerossi.notekeeper.data.local.dao.TaskDao
import com.raerossi.notekeeper.data.local.entities.toDataBase
import com.raerossi.notekeeper.domain.Task
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val dao: TaskDao
) {

    suspend fun insertTask(task: Task): Boolean {
        val idTaskResult = dao.insertTask(task.toDataBase())
        return idTaskResult > 0
    }
}