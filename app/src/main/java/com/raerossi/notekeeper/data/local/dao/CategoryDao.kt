package com.raerossi.notekeeper.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.raerossi.notekeeper.data.local.Tables
import com.raerossi.notekeeper.data.local.entities.CategoryEntity
import com.raerossi.notekeeper.domain.Category

@Dao
interface CategoryDao {

    @Insert
    suspend fun insertCategory(category: CategoryEntity)

    @Query("Delete from " + Tables.CATEGORY + " where id =:id")
    suspend fun deleteCategory(id: Int)

    @Query("Select * from " + Tables.CATEGORY)
    suspend fun getAllCategories(): List<CategoryEntity>
}