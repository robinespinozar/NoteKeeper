package com.raerossi.notekeeper.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raerossi.notekeeper.data.local.dao.CategoryDao
import com.raerossi.notekeeper.data.local.dao.TaskDao
import com.raerossi.notekeeper.data.local.entities.CategoryEntity
import com.raerossi.notekeeper.data.local.entities.TaskEntity

@Database(entities = [TaskEntity::class, CategoryEntity::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao
    abstract fun getCategoryDao(): CategoryDao
}