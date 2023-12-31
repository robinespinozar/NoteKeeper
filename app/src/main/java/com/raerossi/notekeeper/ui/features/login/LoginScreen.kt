package com.raerossi.notekeeper.ui.features.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.raerossi.notekeeper.R
import com.raerossi.notekeeper.ui.features.utils.EmailInputField
import com.raerossi.notekeeper.ui.features.utils.ErrorDialog
import com.raerossi.notekeeper.ui.features.utils.GradientButton
import com.raerossi.notekeeper.ui.features.utils.HorizontalSpacer
import com.raerossi.notekeeper.ui.features.utils.InputField
import com.raerossi.notekeeper.ui.features.utils.LinkButton
import com.raerossi.notekeeper.ui.features.utils.LoadingScreen
import com.raerossi.notekeeper.ui.features.utils.PasswordInputField
import com.raerossi.notekeeper.ui.features.utils.VerticalSpacer
import com.raerossi.notekeeper.ui.theme.NoteKeeperTheme
import com.raerossi.notekeeper.ui.theme.generalSansFamily
import com.raerossi.notekeeper.ui.theme.primaryGradient

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onLoginClick: (Boolean) -> Unit
) {
    val email by loginViewModel.email.observeAsState("")
    val password by loginViewModel.password.observeAsState("")
    val isLoginEnabled by loginViewModel.isLoginEnabled.observeAsState(false)
    val isLoading by loginViewModel.isLoading.observeAsState(false)
    val showErrorDialog by loginViewModel.showErrorDialog.observeAsState(false)
    val messageError by loginViewModel.messageError.observeAsState("")

    LoginScreen(
        email = email,
        password = password,
        isLoginEnabled = isLoginEnabled,
        isLoading = isLoading,
        showErrorDialog = showErrorDialog,
        messageError = messageError,
        onLoginChanged = { email, password -> loginViewModel.onLoginChanged(email, password) },
        onLoginClick = { loginViewModel.onLoginSelected(email, password) { onLoginClick(it) } },
        onSignUpClick = { },
        onErrorDialog = { loginViewModel.hideErrorDialog() }
    )
}

@Composable
fun LoginScreen(
    email: String,
    password: String,
    isLoginEnabled: Boolean,
    isLoading: Boolean,
    showErrorDialog: Boolean,
    messageError: String,
    onErrorDialog: () -> Unit,
    onLoginChanged: (String, String) -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    if (isLoading) {
        LoadingScreen()
    } else {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
        ) {
            LoginHeader(modifier = Modifier.align(Alignment.TopCenter))
            LoginBody(
                modifier = Modifier.align(Alignment.Center),
                email = email,
                password = password,
                isLoginEnabled = isLoginEnabled,
                onLoginChanged = { email, password -> onLoginChanged(email, password) },
                onLoginSelected = { onLoginClick() }
            )
            LoginFooter(
                modifier = Modifier.align(Alignment.BottomCenter),
                onSignUpClick = { onSignUpClick() }
            )
            ErrorDialog(
                show = showErrorDialog,
                message = messageError,
                onDissmis = { onErrorDialog() }
            )
        }
    }
}

@Composable
fun LoginHeader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .paint(
                painterResource(id = R.drawable.bg_login_header),
                contentScale = ContentScale.FillBounds
            )
            .padding(bottom = 8.dp)
    ) {
    }
}

@Composable
fun LoginBody(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    isLoginEnabled: Boolean,
    onLoginChanged: (String, String) -> Unit,
    onLoginSelected: () -> Unit
) {
    Column(modifier = modifier.padding(start = 16.dp, end = 16.dp, bottom = 64.dp)) {
        ImageAndNameLogo(Modifier.align(Alignment.CenterHorizontally))
        VerticalSpacer(16)
        Email(email) { onLoginChanged(it, password) }
        VerticalSpacer(8)
        Password(password) { onLoginChanged(email, it) }
        VerticalSpacer(8)
        ForgotPassword(Modifier.align(Alignment.End))
        VerticalSpacer(16)
        LoginButton(isLoginEnabled) { onLoginSelected() }
        VerticalSpacer(32)
        LoginDivider()
        VerticalSpacer(16)
        SocialLogin(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onTwitterClick = {},
            onGmailClick = {},
            onFaceBookClick = {}
        )
    }
}

@Composable
fun ImageAndNameLogo(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.padding(start = 16.dp),
        painter = painterResource(id = R.drawable.small_logo_green),
        contentDescription = "logo"
    )
    VerticalSpacer(4)
    Text(
        modifier = modifier,
        text = "NoteKeeper",
        style = MaterialTheme.typography.displaySmall,
        color = Color(0xFF0C212C)
    )
}

@Composable
private fun Email(
    email: String,
    onTextChanged: (String) -> Unit
) {
    EmailInputField(email = email, onTextChanged = { onTextChanged(it) })
}

@Composable
private fun Password(
    password: String,
    onTextChanged: (String) -> Unit
) {
    PasswordInputField(password = password, onTextChanged = { onTextChanged(it) })
}

@Composable
private fun ForgotPassword(modifier: Modifier = Modifier) {
    Text(
        text = "Forgot password?",
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = generalSansFamily,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
        color = Color(0xFF006E08),
        modifier = modifier
    )
}

@Composable
private fun LoginButton(
    isLoginEnabled: Boolean,
    onLoginClick: () -> Unit
) {
    GradientButton(
        text = "Log In",
        textColor = Color.White,
        enabled = isLoginEnabled,
        gradient = MaterialTheme.colorScheme.primaryGradient,
        onClick = { onLoginClick() }
    )
}

@Composable
fun LoginDivider(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            Modifier
                .background(Color(0xFFBEC9C7))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 18.dp),
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFFBEC9C7)
        )
        Divider(
            Modifier
                .background(Color(0xFFBEC9C7))
                .height(1.dp)
                .weight(1f)
        )
    }
}

@Composable
fun SocialLogin(
    modifier: Modifier = Modifier,
    onTwitterClick: () -> Unit,
    onGmailClick: () -> Unit,
    onFaceBookClick: () -> Unit
) {
    Row(modifier = modifier) {

        IconButton(onClick = { onTwitterClick() }) {
            Image(
                painter = painterResource(id = R.drawable.twitter),
                contentDescription = "twitter"
            )
        }
        HorizontalSpacer(24)
        IconButton(onClick = { onGmailClick() }) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "twitter"
            )
        }
        HorizontalSpacer(24)
        IconButton(onClick = { onFaceBookClick() }) {
            Image(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = "twitter"
            )
        }
    }
}

@Composable
fun LoginFooter(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .paint(
                painterResource(id = R.drawable.bg_login_footer),
                contentScale = ContentScale.FillBounds
            )
            .padding(bottom = 8.dp)
    ) {
        LinkButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            textDescription = "Don´t have an account",
            textAction = "Sign Up",
            onClick = { onSignUpClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreviews() {
    NoteKeeperTheme {
        LoginScreen {}
    }
}
