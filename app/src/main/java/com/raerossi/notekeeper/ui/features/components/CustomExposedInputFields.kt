package com.raerossi.notekeeper.ui.features.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun TextExposedInputField(
    modifier: Modifier = Modifier,
    text: String,
    textLabel: String? = null,
    textPlaceHolder: String,
    textSupport: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    width: Int? = null,
    height: Int = 48,
    maxLines: Int = 1,
    isError: Boolean = false,
    onTextChanged: (String) -> Unit
){
    BasicInputField(
        modifier = modifier,
        text = text,
        onTextChanged = { onTextChanged(it) },
        isError = isError,
        textSupport = textSupport,
        textLabel = textLabel,
        textPlaceHolder = textPlaceHolder,
        width = width,
        height = height,
        maxLines = maxLines,
        enabled = enabled,
        readOnly = readOnly,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}