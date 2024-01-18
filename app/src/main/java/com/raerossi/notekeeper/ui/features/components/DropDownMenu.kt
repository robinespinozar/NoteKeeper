package com.raerossi.notekeeper.ui.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.raerossi.notekeeper.R
import com.raerossi.notekeeper.ui.theme.errorColor
import com.raerossi.notekeeper.ui.theme.neutral95
import com.raerossi.notekeeper.ui.theme.neutralVariant30

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(modifier: Modifier = Modifier, textLabel: String, items: List<String>) {
    var selectedText by rememberSaveable { mutableStateOf(items[0]) }
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var rowSize by remember { mutableStateOf(Size.Zero) }

    Column(modifier) {
        InputField(
            text = selectedText,
            onTextChanged = { selectedText = it },
            textLabel = textLabel,
            textPlaceHolder = "",
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .onGloballyPositioned { layoutCoordinates ->
                    rowSize = layoutCoordinates.size.toSize()
                }
                .fillMaxWidth()
                .clickable { isExpanded = true },
            trailingIcon = {
                IconButton(onClick = { isExpanded = true }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.dropdown_icon),
                        contentDescription = "DropDown Icon",
                        tint = Color.Unspecified
                    )
                }
            }
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier
                .background(Color.White)
                .width(with(LocalDensity.current) {
                    rowSize.width.toDp()
                })
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    modifier = Modifier.padding(start = 8.dp),
                    text = { Text(text = item, style = MaterialTheme.typography.labelSmall) },
                    onClick = {
                        isExpanded = false
                        selectedText = item
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = MaterialTheme.colorScheme.neutralVariant30
                    )
                )
            }
        }
    }
}