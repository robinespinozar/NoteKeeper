package com.raerossi.notekeeper.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.raerossi.notekeeper.data.local.Tables
import com.raerossi.notekeeper.data.local.entities.TaskEntity

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(task: TaskEntity)

    @Query("Delete from " + Tables.TASK + " where id =:id")
    suspend fun deleteTask(id: Int)

    @Query("Select * from " + Tables.TASK)
    suspend fun getAllTasks(): List<TaskEntity>
}