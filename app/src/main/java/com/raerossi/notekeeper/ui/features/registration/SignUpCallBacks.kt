package com.raerossi.notekeeper.ui.features.registration

data class SignUpCallBacks(
    val onSignUpUserChanged: (SignUpUser) -> Unit,
    val onSignUpClick: (SignUpUser) -> Unit,
    val onLogInClick: () -> Unit,
    val onTwitterClick: () -> Unit,
    val onGmailClick: () -> Unit,
    val onFaceBookClick: () -> Unit,
)