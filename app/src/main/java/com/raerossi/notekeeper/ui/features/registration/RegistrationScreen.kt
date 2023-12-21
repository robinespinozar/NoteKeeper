package com.raerossi.notekeeper.ui.features.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.raerossi.notekeeper.R
import com.raerossi.notekeeper.ui.features.login.ImageAndNameLogo
import com.raerossi.notekeeper.ui.features.login.LoginDivider
import com.raerossi.notekeeper.ui.features.login.SocialLogin
import com.raerossi.notekeeper.ui.features.utils.EmailInputField
import com.raerossi.notekeeper.ui.features.utils.GradientButton
import com.raerossi.notekeeper.ui.features.utils.LinkButton
import com.raerossi.notekeeper.ui.features.utils.PasswordInputField
import com.raerossi.notekeeper.ui.features.utils.TextInputField
import com.raerossi.notekeeper.ui.features.utils.VerticalSpacer
import com.raerossi.notekeeper.ui.theme.primaryGradient

@Composable
fun RegistrationScreen(registrationViewModel: RegistrationViewModel = hiltViewModel()) {
    val name by registrationViewModel.name.observeAsState("")
    val email by registrationViewModel.email.observeAsState("")
    val password by registrationViewModel.password.observeAsState("")
    val confirmedPassword by registrationViewModel.confirmedPassword.observeAsState("")
    val isRegistrationEnabled by registrationViewModel.isRegistrationEnabled.observeAsState(false)
    val isPasswordsMatch by registrationViewModel.isPasswordsMatch.observeAsState(false)

    RegistrationScreen(
        name = name,
        email = email,
        password = password,
        confirmedPassword = confirmedPassword,
        isRegistrationEnabled = isRegistrationEnabled,
        isPasswordsMatch = isPasswordsMatch,
        onSignUpChanged = { name, email, pass, confirmedPass ->
            registrationViewModel.onSignUpChanged(name, email, pass, confirmedPass)
        },
        onSignUpClick = { registrationViewModel.onSignUpSelected(password,confirmedPassword) },
        onLogInClick = { }
    )
}

@Composable
fun RegistrationScreen(
    name: String,
    email: String,
    password: String,
    confirmedPassword: String,
    isPasswordsMatch: Boolean,
    isRegistrationEnabled: Boolean,
    onSignUpChanged: (String, String, String, String) -> Unit,
    onSignUpClick: () -> Unit,
    onLogInClick: () -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
    ) {
        RegistrationHeader(modifier = Modifier.align(Alignment.TopCenter))
        RegistrationBody(
            modifier = Modifier.align(Alignment.Center),
            name = name,
            email = email,
            password = password,
            confirmedPassword = confirmedPassword,
            isPasswordsMatch = isPasswordsMatch,
            isRegistrationEnabled = isRegistrationEnabled,
            onSignUpChanged = { name, email, pass, confirmedPass ->
                onSignUpChanged(name, email, pass, confirmedPass)
            },
            onSignUpSelected = { onSignUpClick() }
        )
        RegistrationFooter(
            modifier = Modifier.align(Alignment.BottomCenter),
            onLogInClick = { onLogInClick() })
    }
}

@Composable
fun RegistrationHeader(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .paint(
                painterResource(id = R.drawable.bg_login_header),
                contentScale = ContentScale.FillBounds
            )
            .padding(bottom = 8.dp)
    ) {}
}

@Composable
fun RegistrationBody(
    modifier: Modifier = Modifier,
    name: String,
    email: String,
    password: String,
    confirmedPassword: String,
    isRegistrationEnabled: Boolean,
    isPasswordsMatch: Boolean,
    onSignUpChanged: (String, String, String, String) -> Unit,
    onSignUpSelected: () -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, bottom = 64.dp)
    ) {
        ImageAndNameLogo(Modifier.align(Alignment.CenterHorizontally))
        VerticalSpacer(16)
        Name(name) { onSignUpChanged(it, email, password, confirmedPassword) }
        VerticalSpacer(8)
        Email(email) { onSignUpChanged(name, it, password, confirmedPassword) }
        VerticalSpacer(8)
        Password(password) { onSignUpChanged(name, email, it, confirmedPassword) }
        VerticalSpacer(8)
        ConfirmedPassword(confirmedPassword, isPasswordsMatch) {
            onSignUpChanged(name, email, password, it)
        }
        VerticalSpacer(16)
        RegisterButton(isRegistrationEnabled) { onSignUpSelected() }
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
private fun Name(
    name: String,
    onTextChanged: (String) -> Unit
) {
    TextInputField(
        text = name,
        textLabel = "Name",
        textPlaceHolder = "Enter your name",
        onTextChanged = { onTextChanged(it) }
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
    PasswordInputField(
        password = password,
        textSupport = "Must be 7 or more characters",
        onTextChanged = { onTextChanged(it) }
    )
}

@Composable
fun ConfirmedPassword(
    confirmedPassword: String,
    isPasswordsMatch: Boolean,
    onTextChanged: (String) -> Unit
) {
    PasswordInputField(
        password = confirmedPassword,
        textLabel = "Confirm Password",
        textPlaceHolder = "Confirm your Password",
        textSupport = if (!isPasswordsMatch) "Passwords don't match" else null,
        isError = !isPasswordsMatch,
        onTextChanged = { onTextChanged(it) }
    )
}

@Composable
fun RegisterButton(
    isRegistrationEnabled: Boolean,
    onRegistrationClick: () -> Unit
) {
    GradientButton(
        text = "Sign Up",
        textColor = Color.White,
        enabled = isRegistrationEnabled,
        gradient = MaterialTheme.colorScheme.primaryGradient,
        onClick = { onRegistrationClick() }
    )
}

@Composable
fun RegistrationFooter(
    modifier: Modifier = Modifier,
    onLogInClick: () -> Unit
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
            textDescription = "Already have an account",
            textAction = "Log In",
            onClick = { onLogInClick() }
        )
    }
}
