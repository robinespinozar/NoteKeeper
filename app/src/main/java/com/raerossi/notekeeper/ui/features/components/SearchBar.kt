package com.raerossi.notekeeper.ui.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raerossi.notekeeper.R
import com.raerossi.notekeeper.ui.theme.generalSansFamily
import com.raerossi.notekeeper.ui.theme.neutral95
import com.raerossi.notekeeper.ui.theme.primary50
import com.raerossi.notekeeper.ui.theme.primary60
import com.raerossi.notekeeper.ui.theme.robotoFamily

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    textFilter: String,
    onSearchTextChanged: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp)
            .height(48.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp)
            )
            .border(
                width = 0.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(24.dp)
            ),
        value = textFilter,
        onValueChange = { onSearchTextChanged(it) },
        textStyle = MaterialTheme.typography.labelLarge,
        singleLine = true,
        enabled = true,
        leadingIcon = {
            IconButton(onClick = { keyboardController?.hide() }) {
                Icon(
                    modifier = Modifier.padding(start = 6.dp),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "search bar",
                    tint = MaterialTheme.colorScheme.primary60
                )
            }
        },
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.primary50,
            unfocusedTextColor = MaterialTheme.colorScheme.primary50,
            focusedContainerColor = MaterialTheme.colorScheme.neutral95,
            unfocusedContainerColor = MaterialTheme.colorScheme.neutral95,
            cursorColor = MaterialTheme.colorScheme.primary50,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = "Search Task ...",
                color = MaterialTheme.colorScheme.primary50,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.15.sp
            )
        }
    )
}