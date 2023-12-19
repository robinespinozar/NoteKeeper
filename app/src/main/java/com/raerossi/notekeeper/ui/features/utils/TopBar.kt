package com.raerossi.notekeeper.ui.features.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.raerossi.notekeeper.R
import com.raerossi.notekeeper.ui.theme.neutralVariant95
import com.raerossi.notekeeper.ui.theme.primary50
import com.raerossi.notekeeper.ui.theme.topBarContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressTopBar(
    modifier: Modifier = Modifier,
    progress: Float,
    onBackClick: () -> Unit
) {
    var progressStatus by rememberSaveable { mutableStateOf(progress) }

    CenterAlignedTopAppBar(
        modifier = modifier
            .smallShadow()
            .height(48.dp),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.topBarContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.primary50
        ),
        title = { ProgressBar(progress = progressStatus) },
        navigationIcon = { BackIcon { onBackClick() } }
    )
}

@Composable
private fun ProgressBar(
    modifier: Modifier = Modifier,
    progress: Float
) {
    Box(modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier.background(
                color = Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            )
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .height(5.dp)
                    .width(175.dp),
                progress = progress,
                color = MaterialTheme.colorScheme.primary50,
                trackColor = MaterialTheme.colorScheme.neutralVariant95
            )
        }
    }
}

@Composable
private fun BackIcon(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Box(modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
        IconButton(
            onClick = { onBackClick() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = "Back Arrow"
            )
        }
    }
}