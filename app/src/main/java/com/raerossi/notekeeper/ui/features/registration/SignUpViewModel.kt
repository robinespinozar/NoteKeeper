package com.raerossi.notekeeper.ui.features.registration

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raerossi.notekeeper.domain.usecases.CreateAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val createAccountUseCase: CreateAccountUseCase
) : ViewModel() {

    private val _signUpUser = MutableLiveData<SignUpUser>()
    val signUpUser: LiveData<SignUpUser> = _signUpUser

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState

    private val _showErrorDialog = MutableLiveData<Boolean>()
    val showErrorDialog: LiveData<Boolean> = _showErrorDialog

    private val _messageError = MutableLiveData<String>()
    val messageError: LiveData<String> = _messageError

    fun onSignUpSelected(signUpUser: SignUpUser, toVerifyEmail: () -> Unit) {
        val uiState = signUpUser.toSignUpUiState()
        if (uiState.validateUser()) {
            createAccount(signUpUser) { toVerifyEmail() }
        } else {
            onUiStateChanged(signUpUser)
        }
    }

    private fun onUiStateChanged(signUpUser: SignUpUser) {
        _uiState.value = signUpUser.toSignUpUiState()
    }

    private fun createAccount(signUpUser: SignUpUser, toVerifyEmail: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState(isLoading = true)
            try {
                val isCreationSuccessful = createAccountUseCase(signUpUser)
                if (isCreationSuccessful) toVerifyEmail() else _showErrorDialog.value = true
            } catch (e: Exception) {
                _showErrorDialog.value = true
                _messageError.value = e.message.orEmpty()
            }
            _uiState.value = SignUpUiState(isLoading = false)
        }
    }

    fun onSignUpUserChanged(signUpUser: SignUpUser) {
        _signUpUser.value = signUpUser
    }

    fun hideErrorDialog() {
        _showErrorDialog.value = false
    }

    private fun validateName(name: String) =
        name.matches(Regex("^[a-zA-Z ]+\$")) && name.isNotEmpty()

    private fun validateEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun validatePassword(password: String) = password.length > 6

    private fun validatePasswordMatches(password: String, confirmedPassword: String) =
        password == confirmedPassword

    fun SignUpUser.toSignUpUiState(): SignUpUiState {
        return SignUpUiState(
            isValidName = validateName(this.name),
            isValidEmail = validateEmail(this.email),
            isValidPassword = validatePassword(this.password),
            isPasswordsMatch = validatePasswordMatches(this.password, this.confirmedPassword)
        )
    }
}
