package com.raerossi.notekeeper.domain.usecases

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.raerossi.notekeeper.data.CategoryRepository
import com.raerossi.notekeeper.domain.Category
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(): List<Category> {
        val categories = repository.getAllCategories()

        if (categories.isEmpty()) {
            val defaultCategory = createDefaultCategory()
            repository.insertCategory(defaultCategory)
            return listOf(defaultCategory)
        }

        return categories
    }

    private fun createDefaultCategory() = Category(
        name = "No Category",
        pending = 0,
        completed = 0,
        total = 0,
        hexColor1 = "#2D3ED9",
        hexColor2 = "#18B2E2"
    )
}