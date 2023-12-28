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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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
fun SignUpScreen(signUpViewModel: SignUpViewModel = hiltViewModel()) {
    val signUpUser by signUpViewModel.signUpUser.observeAsState(SignUpUser())
    val uiState by signUpViewModel.uiState.collectAsState()

    SignUpScreen(
        signUpUser = signUpUser,
        uiState = uiState,
        callBacks = SignUpCallBacks(
            onSignUpUserChanged = { signUpViewModel.onSignUpUserChanged(it) },
            onSignUpClick = { signUpViewModel.onSignUpSelected(it, toVerifyEmail = { }) },
            onLogInClick = {},
            onTwitterClick = {},
            onGmailClick = {},
            onFaceBookClick = {},
        )
    )
}

@Composable
fun SignUpScreen(
    signUpUser: SignUpUser,
    uiState: SignUpUiState,
    callBacks: SignUpCallBacks
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
    ) {
        SignUpHeader(modifier = Modifier.align(Alignment.TopCenter))
        SignUpBody(
            modifier = Modifier.align(Alignment.Center),
            signUpUser = signUpUser,
            uiState = uiState,
            callBacks = callBacks
        )
        SignUpFooter(
            modifier = Modifier.align(Alignment.BottomCenter),
            onLogInClick = { callBacks.onLogInClick() })
    }
}

@Composable
fun SignUpHeader(modifier: Modifier) {
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
fun SignUpBody(
    modifier: Modifier = Modifier,
    signUpUser: SignUpUser,
    uiState: SignUpUiState,
    callBacks: SignUpCallBacks
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, bottom = 64.dp)
    ) {
        ImageAndNameLogo(Modifier.align(Alignment.CenterHorizontally))
        VerticalSpacer(16)
        Name(
            name = signUpUser.name,
            isValidName = uiState.isValidName
        ) { callBacks.onSignUpUserChanged(signUpUser.copy(name = it)) }
        VerticalSpacer(8)
        Email(
            email = signUpUser.email,
            isValidEmail = uiState.isValidEmail
        ) { callBacks.onSignUpUserChanged(signUpUser.copy(email = it)) }
        VerticalSpacer(8)
        Password(
            password = signUpUser.password,
            isValidPassword = uiState.isValidPassword
        ) { callBacks.onSignUpUserChanged(signUpUser.copy(password = it)) }
        VerticalSpacer(8)
        ConfirmedPassword(
            confirmedPassword = signUpUser.confirmedPassword,
            isPasswordsMatch = uiState.isPasswordsMatch
        ) { callBacks.onSignUpUserChanged(signUpUser.copy(confirmedPassword = it)) }
        VerticalSpacer(16)
        RegisterButton { callBacks.onSignUpClick(signUpUser.copy()) }
        VerticalSpacer(32)
        LoginDivider()
        VerticalSpacer(16)
        SocialLogin(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onTwitterClick = { callBacks.onTwitterClick() },
            onGmailClick = { callBacks.onGmailClick() },
            onFaceBookClick = { callBacks.onFaceBookClick() }
        )
    }
}

@Composable
private fun Name(
    name: String,
    isValidName: Boolean,
    onTextChanged: (String) -> Unit
) {
    TextInputField(
        text = name,
        textLabel = "Name",
        textPlaceHolder = "Enter your name",
        textSupport = if (!isValidName) "Enter a valid name" else null,
        isError = !isValidName,
        onTextChanged = { onTextChanged(it) }
    )
}

@Composable
private fun Email(
    email: String,
    isValidEmail: Boolean,
    onTextChanged: (String) -> Unit
) {
    EmailInputField(
        email = email,
        textSupport = if (!isValidEmail) "Enter a valid email address" else null,
        isError = !isValidEmail,
        onTextChanged = { onTextChanged(it) }
    )
}

@Composable
private fun Password(
    password: String,
    isValidPassword: Boolean,
    onTextChanged: (String) -> Unit
) {
    PasswordInputField(
        password = password,
        textSupport = if (!isValidPassword) "Password must be 7 or more characters" else "Must be 7 or more characters",
        isError = !isValidPassword,
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
    onSignUpClick: () -> Unit
) {
    GradientButton(
        text = "Sign Up",
        textColor = Color.White,
        gradient = MaterialTheme.colorScheme.primaryGradient,
        onClick = { onSignUpClick() }
    )
}

@Composable
fun SignUpFooter(
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
