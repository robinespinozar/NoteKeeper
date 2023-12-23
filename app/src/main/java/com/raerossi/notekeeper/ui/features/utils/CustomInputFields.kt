package com.raerossi.notekeeper.ui.features.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.raerossi.notekeeper.ui.theme.errorColor

@Composable
fun PasswordInputField(
    password: String,
    textLabel: String = "Password",
    textPlaceHolder: String = "Enter your password",
    textSupport: String? = null,
    isError: Boolean = false,
    onTextChanged: (String) -> Unit
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    InputField(
        modifier = Modifier.fillMaxWidth(),
        text = password,
        onTextChanged = { onTextChanged(it) },
        textLabel = textLabel,
        isError = isError,
        textSupport = textSupport,
        textPlaceHolder = textPlaceHolder,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }
            ) {
                Icon(
                    imageVector = if (passwordVisibility) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                    tint = if (isError) MaterialTheme.colorScheme.errorColor else MaterialTheme.colorScheme.outlineVariant,
                    contentDescription = "show password"
                )
            }
        },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun EmailInputField(
    email: String,
    textLabel: String = "Email",
    textPlaceHolder: String = "Enter your email",
    textSupport: String? = null,
    isError: Boolean = false,
    onTextChanged: (String) -> Unit
) {
    InputField(
        modifier = Modifier.fillMaxWidth(),
        text = email,
        onTextChanged = { onTextChanged(it) },
        isError = isError,
        textSupport = textSupport,
        textLabel = textLabel,
        textPlaceHolder = textPlaceHolder,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun TextInputField(
    text: String,
    textLabel: String,
    textPlaceHolder: String,
    textSupport: String? = null,
    isError: Boolean = false,
    onTextChanged: (String) -> Unit
) {
    InputField(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        onTextChanged = { onTextChanged(it) },
        isError = isError,
        textSupport = textSupport,
        textLabel = textLabel,
        textPlaceHolder = textPlaceHolder,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}