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

    private val _isCreationSuccessful = MutableLiveData<Boolean>()
    val isCreationSuccessful: LiveData<Boolean> = _isCreationSuccessful

    fun onSignUpSelected(signUpUser: SignUpUser) {
        val uiState = signUpUser.toSignUpUiState()
        if (uiState.validateUser()) createAccount(signUpUser) else onUiStateChanged(signUpUser)
    }

    private fun onUiStateChanged(signUpUser: SignUpUser) {
        _uiState.value = signUpUser.toSignUpUiState()
    }

    private fun createAccount(signUpUser: SignUpUser) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState(isLoading = false)
            _isCreationSuccessful.value = createAccountUseCase(signUpUser)
            _uiState.value = SignUpUiState(isLoading = true)
        }
    }

    fun onSignUpUserChanged(signUpUser: SignUpUser){
        _signUpUser.value = signUpUser
    }

    private fun validateName(name: String) = name.matches(Regex("^[a-zA-Z ]+\$")) && name.isNotEmpty()

    private fun validateEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun validatePassword(password: String) = password.length > 6

    private fun validatePasswordMatches(password: String, confirmedPassword: String) = password == confirmedPassword

    fun SignUpUser.toSignUpUiState(): SignUpUiState {
        return SignUpUiState(
            isValidName = validateName(this.name),
            isValidEmail = validateEmail(this.email),
            isValidPassword = validatePassword(this.password),
            isPasswordsMatch = validatePasswordMatches(this.password, this.confirmedPassword)
        )
    }
}
