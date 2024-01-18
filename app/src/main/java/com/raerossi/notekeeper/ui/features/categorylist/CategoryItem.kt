package com.raerossi.notekeeper.ui.features.categorylist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.raerossi.notekeeper.R
import com.raerossi.notekeeper.domain.Category
import com.raerossi.notekeeper.ui.features.components.VerticalSpacer
import com.raerossi.notekeeper.ui.features.components.categoryShadow
import com.raerossi.notekeeper.ui.theme.NoteKeeperTheme
import com.raerossi.notekeeper.ui.theme.neutralVariant90
import com.raerossi.notekeeper.ui.theme.neutralVariant95
import com.raerossi.notekeeper.ui.theme.primary70

@Composable
fun CategoryItem(category: Category? = null, height: Int = 125, width: Int? = null) {
    val modifier = if (width == null) Modifier.fillMaxWidth() else Modifier.width(width.dp)

    Card(
        modifier = modifier
            .categoryShadow()
            .height(height.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        CardContent(category, height)
    }
}

@Composable
private fun CardContent(category: Category?, height: Int) {
    Column() {
        CategoryInformation(category, (height * 0.6).dp)
        CategoryAdvance(category, (height * 0.4).dp)
    }
}

@Composable
private fun CategoryInformation(category: Category?, height: Dp) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(height)
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
private fun CategoryAdvance(category: Category?, height: Dp) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(height)
            .background(Color.White)
    ) {
        Column(
            Modifier
                .align(Alignment.BottomStart)
        ) {
            Text(
                modifier = Modifier.padding(start = 18.dp),
                text = "60%",
                color = Color(0xB35B6158),
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


@Composable
fun AddCategoryItem(modifier: Modifier = Modifier, size: Int = 125) {
    Card(
        modifier = modifier
            .categoryShadow()
            .height(size.dp)
            .width(size.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.neutralVariant90)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_new_category),
                    contentDescription = "Add New Category",
                    tint = Color(0xFF0C212C)
                )
                VerticalSpacer(16)
                Text(
                    text = "Add New \n" + "Category",
                    textAlign = TextAlign.Center,
                    color = Color(0xFF0C212C),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
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
        ) {
            Column {
                CategoryItem(width = 203)
                VerticalSpacer(32)
                AddCategoryItem()
            }
        }
    }
}