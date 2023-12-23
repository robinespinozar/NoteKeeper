package com.raerossi.notekeeper.ui.features.registration

data class SignUpUiState(
    val isLoading: Boolean = false,
    val isValidName: Boolean = true,
    val isValidEmail: Boolean = true,
    val isValidPassword: Boolean = true,
    val isPasswordsMatch: Boolean = true
) {
    fun validateUser() = isValidName && isValidEmail && isValidPassword && isPasswordsMatch
}