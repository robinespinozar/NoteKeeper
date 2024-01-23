package com.raerossi.notekeeper.data

import com.raerossi.notekeeper.data.local.dao.CategoryDao
import com.raerossi.notekeeper.data.local.entities.toDataBase
import com.raerossi.notekeeper.domain.Category
import com.raerossi.notekeeper.domain.toDomain
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val dao: CategoryDao
) {

    suspend fun getAllCategories(): List<Category> {
        val response = dao.getAllCategories()
        return response.map { it.toDomain() }
    }

    suspend fun insertCategory(category: Category) {
        dao.insertCategory(category.toDataBase())
    }
}