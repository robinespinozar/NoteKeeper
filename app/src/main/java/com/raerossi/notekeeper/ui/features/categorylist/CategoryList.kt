package com.raerossi.notekeeper.ui.features.categorylist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.raerossi.notekeeper.domain.Category
import com.raerossi.notekeeper.domain.Task

@Composable
fun CategoryList(
    modifier: Modifier = Modifier,
    widthItem: Int? = null,
    categoryList: List<Category>
) {
    LazyRow(
        modifier = modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categoryList) { category ->
            CategoryItem(category = category, width = widthItem)
        }
        item {
            AddCategoryItem()
        }
    }
}
