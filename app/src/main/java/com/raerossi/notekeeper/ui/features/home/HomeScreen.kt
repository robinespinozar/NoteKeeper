package com.raerossi.notekeeper.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raerossi.notekeeper.domain.Category
import com.raerossi.notekeeper.domain.Task
import com.raerossi.notekeeper.ui.features.components.HorizontalSpacer
import com.raerossi.notekeeper.ui.features.components.SearchBar
import com.raerossi.notekeeper.ui.features.components.Sizes
import com.raerossi.notekeeper.ui.features.components.TitleAndDescription
import com.raerossi.notekeeper.ui.features.components.VerticalSpacer
import com.raerossi.notekeeper.ui.theme.NoteKeeperTheme
import com.raerossi.notekeeper.ui.theme.generalSansFamily
import com.raerossi.notekeeper.ui.theme.primary40

@Composable
fun HomeScreen(
    categoriesList: List<Category>? = null,
    tasksList: List<Task>? = null,
    onAddClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HomeHeader()
        HomeBody(categoriesList = categoriesList, tasksList = tasksList, onAddClick = { onAddClick() })
    }
}

@Composable
fun HomeHeader(modifier: Modifier = Modifier) {
    Column {
        VerticalSpacer(16)
        TitleAndDescription(
            title = "Hi, Robin!",
            description = "lunes 03, 2023",
            size = Sizes.sizeMed
        )
        VerticalSpacer(16)
        SearchBar(textFilter = "") {}
        VerticalSpacer(24)
    }
}

@Composable
fun HomeBody(
    modifier: Modifier = Modifier,
    categoriesList: List<Category>?,
    tasksList: List<Task>?,
    onAddClick: () -> Unit
) {
    Column {
        CategoriesContent(categoriesList)
        VerticalSpacer(24)
        TasksContent(tasksList, onAddClick = { onAddClick() })
    }
}

@Composable
fun CategoriesContent(categoriesList: List<Category>?) {
    Column(Modifier.padding(horizontal = 16.dp)) {
        TitleText("Categories")
        VerticalSpacer(8)
        //CategoryList(categoryList = categoriesList)
    }
}

@Composable
fun TasksContent(tasksList: List<Task>?, onAddClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TitleText("Today Tasks")
        HorizontalSpacer(8)
        Text(text = "8", color = Color(0xB35B6158), style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.weight(1f))
        TextButton(text = "Add") { onAddClick() }
        TextButton(text = "View All") {}
    }
    VerticalSpacer(8)
    //TaskList(tasksList = tasksList)
}

@Composable
private fun TextButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    TextButton(
        onClick = { onClick() },
        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary40)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = generalSansFamily,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
        )
    }
}

@Composable
fun TitleText(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = Color(0xFF363942)
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    NoteKeeperTheme {
        Box(Modifier.fillMaxSize()) {
            HomeScreen(){}
        }
    }
}