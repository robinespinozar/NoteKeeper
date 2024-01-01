package com.raerossi.notekeeper.ui.features.reestablish

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.raerossi.notekeeper.R
import com.raerossi.notekeeper.ui.features.utils.EmailInputField
import com.raerossi.notekeeper.ui.features.utils.GradientButton
import com.raerossi.notekeeper.ui.features.utils.VerticalSpacer
import com.raerossi.notekeeper.ui.theme.backgroundGradient
import com.raerossi.notekeeper.ui.theme.primary20
import com.raerossi.notekeeper.ui.theme.primaryGradient
import com.raerossi.notekeeper.ui.theme.secondaryGradient

@Composable
fun ReestablishScreen(
    reestablishViewModel: ReestablishViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val email by reestablishViewModel.email.observeAsState("")
    val isValidEmail by reestablishViewModel.isValidEmail.observeAsState(true)

    ReestablishScreen(
        email = email,
        isValidEmail = isValidEmail,
        onEmailChanged = { reestablishViewModel.onEmailChanged(it) },
        onResetClick = {
            reestablishViewModel.recoverPassword(email) { result ->
                Toast.makeText(context, getResultMessage(result), Toast.LENGTH_LONG).show()
                if (result) onBackClick()
            }
        },
        onBackClick = { onBackClick() }
    )
}

@Composable
fun ReestablishScreen(
    email: String,
    onEmailChanged: (String) -> Unit,
    isValidEmail: Boolean,
    onResetClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = MaterialTheme.colorScheme.backgroundGradient)
    ) {
        ReestablishAnimation(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(0.dp, 300.dp)
        )
        ReestablishDescription()
        ReestablishContent(
            email = email,
            onEmailChanged = { onEmailChanged(it) },
            isValidEmail = isValidEmail,
            onResetClick = { onResetClick() },
            onBackClick = { onBackClick() }
        )
    }
}

@Composable
fun ReestablishContent(
    modifier: Modifier = Modifier,
    email: String,
    onEmailChanged: (String) -> Unit,
    isValidEmail: Boolean,
    onResetClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(modifier.padding(horizontal = 16.dp)) {
        VerticalSpacer(24)
        EmailInputField(
            email = email,
            textSupport = if (!isValidEmail) "Enter a valid email address" else null,
            isError = !isValidEmail,
            onTextChanged = { onEmailChanged(it) }
        )
        VerticalSpacer(16)
        GradientButton(
            text = "Reset password",
            textColor = MaterialTheme.colorScheme.primary20,
            gradient = MaterialTheme.colorScheme.secondaryGradient
        ) { onResetClick() }
        VerticalSpacer(16)
        GradientButton(
            text = "Back",
            textColor = Color.White,
            gradient = MaterialTheme.colorScheme.primaryGradient
        ) { onBackClick() }
    }
}

@Composable
fun ReestablishAnimation(modifier: Modifier = Modifier) {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.reset_password))
    val progress by animateLottieCompositionAsState(
        composition = lottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    LottieAnimation(
        composition = lottieComposition,
        progress = progress,
        modifier = modifier
    )
}

@Composable
fun ReestablishDescription(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = "Provide your account's email for which you\n" + "want to reset your password.",
        style = MaterialTheme.typography.bodySmall,
        color = Color.White,
        textAlign = TextAlign.Center
    )
}

private fun getResultMessage(result: Boolean) = if (result) "We sent an email to reset your password" else "We couldn't sent an reset email, please try again."
