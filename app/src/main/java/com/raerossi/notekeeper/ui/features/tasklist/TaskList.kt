package com.raerossi.notekeeper.ui.features.tasklist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raerossi.notekeeper.domain.Task

@Composable
fun TaskList(modifier: Modifier = Modifier, tasksList: List<Task>) {
    LazyColumn(
        modifier = modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tasksList) { task ->
            TaskItem(task)
        }
    }
}