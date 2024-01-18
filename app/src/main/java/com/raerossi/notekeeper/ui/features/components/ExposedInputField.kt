package com.raerossi.notekeeper.ui.features.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.raerossi.notekeeper.ui.theme.description
import com.raerossi.notekeeper.ui.theme.disabled_placeholder
import com.raerossi.notekeeper.ui.theme.errorColor
import com.raerossi.notekeeper.ui.theme.neutral95
import com.raerossi.notekeeper.ui.theme.neutralVariant40
import com.raerossi.notekeeper.ui.theme.primary50

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicInputField(
    modifier: Modifier = Modifier,
    text: String,
    textLabel: String? = null,
    textPlaceHolder: String,
    width: Int? = null,
    height: Int = 48,
    maxLines: Int = 1,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    textSupport: String? = null,
    isPicker: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onTextChanged: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusedIndicatorColor =
        if (isError) MaterialTheme.colorScheme.errorColor else MaterialTheme.colorScheme.primary50
    val isEmpty = !text.isNotEmpty()
    val modifierCustom = if (width == null) modifier.fillMaxWidth() else modifier.width(width.dp)

    Column{
        if(textLabel != null){
            Text(
                text = textLabel,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary50
            )
            VerticalSpacer(8)
        }

        TextField(
            modifier = modifierCustom
                .background(
                    color = if (isFocused) focusedIndicatorColor else Color.Transparent,
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = if (isEmpty) 1.dp else 0.dp,
                    color = if (isFocused || !isEmpty) Color.Transparent else MaterialTheme.colorScheme.neutral95,
                    shape = RoundedCornerShape(12.dp)
                )
                .height(height.dp)
                .clip(RoundedCornerShape(12.dp))
                .onFocusChanged { isFocused = it.isFocused },
            value = text,
            onValueChange = { onTextChanged(it) },
            textStyle = MaterialTheme.typography.bodySmall,
            isError = isError,
            maxLines = maxLines,
            visualTransformation = visualTransformation,
            enabled = enabled,
            readOnly = readOnly,
            keyboardOptions = keyboardOptions,
            placeholder = {
                Text(
                    text = textPlaceHolder,
                    style = MaterialTheme.typography.bodySmall
                )
            },
            shape = RoundedCornerShape(
                topStart = 0.0.dp,
                topEnd = 0.0.dp,
                bottomEnd = 16.0.dp,
                bottomStart = 16.0.dp
            ),
            colors = TextFieldDefaults.colors(
                disabledTextColor = if(isPicker) Color.Black else MaterialTheme.colorScheme.disabled_placeholder,
                disabledPlaceholderColor = MaterialTheme.colorScheme.disabled_placeholder,
                disabledIndicatorColor =  Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.neutral95,
                unfocusedContainerColor = MaterialTheme.colorScheme.neutral95,
                disabledContainerColor = MaterialTheme.colorScheme.neutral95,
                cursorColor = MaterialTheme.colorScheme.primary50,
                errorCursorColor = MaterialTheme.colorScheme.errorColor,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary50,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = MaterialTheme.colorScheme.errorColor,
                errorLabelColor = MaterialTheme.colorScheme.errorColor,
                focusedPlaceholderColor = if (isError) MaterialTheme.colorScheme.errorColor else MaterialTheme.colorScheme.neutralVariant40,
                unfocusedPlaceholderColor = if (isError) MaterialTheme.colorScheme.errorColor else MaterialTheme.colorScheme.neutralVariant40,
            ),
            trailingIcon = {
                if (trailingIcon != null) {
                    trailingIcon()
                }
            }
        )
        if (textSupport != null) {
            VerticalSpacer(4)
            SupportText(
                textSupport = textSupport,
                isError = isError,
                errorSupportColor = MaterialTheme.colorScheme.errorColor,
                supportColor = MaterialTheme.colorScheme.neutralVariant40
            )
        }
    }
}