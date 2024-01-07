package com.raerossi.notekeeper.ui.features.categorylist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raerossi.notekeeper.domain.Category
import com.raerossi.notekeeper.ui.features.utils.ProgressTopBar
import com.raerossi.notekeeper.ui.features.utils.VerticalSpacer
import com.raerossi.notekeeper.ui.features.utils.categoryShadow
import com.raerossi.notekeeper.ui.theme.NoteKeeperTheme
import com.raerossi.notekeeper.ui.theme.neutralVariant40
import com.raerossi.notekeeper.ui.theme.neutralVariant95
import com.raerossi.notekeeper.ui.theme.primary50
import com.raerossi.notekeeper.ui.theme.primary70
import com.raerossi.notekeeper.ui.theme.title

@Composable
fun CategoryItem(category: Category? = null) {
    Card(
        modifier = Modifier
            .categoryShadow()
            .height(125.dp)
            .width(203.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        CardContent(category)
    }
}

@Composable
private fun CardContent(category: Category?) {
    Column() {
        CategoryInformation(category)
        CategoryAdvance(category)
    }
}

@Composable
private fun CategoryInformation(category: Category?) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(75.dp)
            .background(MaterialTheme.colorScheme.primary70)
    ) {
        Column(
            Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 12.dp)
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = "Web Design",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
            VerticalSpacer(4)
            Text(
                modifier = Modifier.padding(start = 18.dp),
                text = "12 Projects",
                color = Color(0xB3FFFFFF),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun CategoryAdvance(category: Category?) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White)
    ) {
        Column(
            Modifier
                .align(Alignment.BottomStart)
        ) {
            Text(
                modifier = Modifier.padding(start = 18.dp),
                text = "60%",
                color = MaterialTheme.colorScheme.neutralVariant40,
                style = MaterialTheme.typography.bodyLarge
            )
            VerticalSpacer(4)
            CategoryProgress(progress = 0.6f)
        }
    }
}

@Composable
private fun CategoryProgress(modifier: Modifier = Modifier, progress: Float) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier
                .padding(start = 18.dp, bottom = 16.dp)
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(6.dp)
                )
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .height(2.dp)
                    .width(100.dp),
                progress = progress,
                color = MaterialTheme.colorScheme.primary70,
                trackColor = MaterialTheme.colorScheme.neutralVariant95
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryItemPreview() {
    NoteKeeperTheme {
        Box(
            Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) { CategoryItem() }
    }
}