package com.raerossi.notekeeper.ui.features.verification

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.raerossi.notekeeper.R
import com.raerossi.notekeeper.ui.features.utils.GradientButton
import com.raerossi.notekeeper.ui.features.utils.LinkButton
import com.raerossi.notekeeper.ui.features.utils.TitleAndDescription
import com.raerossi.notekeeper.ui.features.utils.VerticalSpacer
import com.raerossi.notekeeper.ui.theme.NoteKeeperTheme
import com.raerossi.notekeeper.ui.theme.backgroundGradient
import com.raerossi.notekeeper.ui.theme.primary20
import com.raerossi.notekeeper.ui.theme.secondaryGradient
import com.raerossi.notekeeper.ui.theme.tertiary95

@Composable
fun VerificationScreen(
    verificationViewModel: VerificationViewModel = hiltViewModel(),
    onContinueClick: () -> Unit
) {
    val showContinueButton by verificationViewModel.showContinueButton.observeAsState(false)

    VerificationScreen(
        showContinueButton = showContinueButton,
        onContinueClick = { onContinueClick() },
        onSendEmailClick = { verificationViewModel.sendEmailVerification() }
    )
}

@Composable
fun VerificationScreen(
    showContinueButton: Boolean,
    onContinueClick: () -> Unit,
    onSendEmailClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = MaterialTheme.colorScheme.backgroundGradient)
    ) {
        VerifyAnimation(
            modifier = Modifier
                .padding(bottom = 128.dp)
                .fillMaxWidth()
                .heightIn(0.dp, 350.dp)
                .align(Alignment.Center)
        )
        VerificationContent(
            modifier = Modifier.align(Alignment.BottomCenter),
            showContinueButton = showContinueButton,
            onContinueClick = { onContinueClick() },
            onSendEmailClick = { onSendEmailClick() }
        )
    }
}

@Composable
fun VerificationContent(
    modifier: Modifier = Modifier,
    showContinueButton: Boolean,
    onContinueClick: () -> Unit,
    onSendEmailClick: () -> Unit
) {
    Column(modifier) {
        TitleAndDescription(
            title = "Confirm your email",
            darkMode = true,
            centerAlign = true,
            description = "We just sent you an email to verify your account\n" + "(remember look the SPAM)\n"
        )
        VerticalSpacer(16)
        ContinueButton(showContinueButton) { onContinueClick() }
        VerticalSpacer(8)
        LinkButton(
            modifier = Modifier.padding(bottom = 8.dp),
            textDescription = "Still not receiving the mail?",
            textAction = "Send again",
            descriptionColor = Color.White,
            actionColor = MaterialTheme.colorScheme.tertiary95,
            onClick = { onSendEmailClick() }
        )
    }
}

@Composable
fun ContinueButton(showContinueButton: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = showContinueButton
        ) {
            GradientButton(
                text = "Continue",
                textColor = MaterialTheme.colorScheme.primary20,
                gradient = MaterialTheme.colorScheme.secondaryGradient,
                onClick = { onClick() }
            )
        }
    }
}

@Composable
fun VerifyAnimation(modifier: Modifier = Modifier) {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.verify_email))
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