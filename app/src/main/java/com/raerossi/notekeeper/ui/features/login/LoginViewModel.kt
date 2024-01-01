package com.raerossi.notekeeper.ui.features.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raerossi.notekeeper.R
import com.raerossi.notekeeper.data.remote.LoginResult
import com.raerossi.notekeeper.domain.usecases.LoginUseCase
import com.raerossi.notekeeper.domain.usecases.RecoverPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLoginEnabled = MutableLiveData<Boolean>()
    val isLoginEnabled: LiveData<Boolean> = _isLoginEnabled

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _showErrorDialog = MutableLiveData<Boolean>()
    val showErrorDialog: LiveData<Boolean> = _showErrorDialog

    private val _messageError = MutableLiveData<String>()
    val messageError: LiveData<String> = _messageError

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLoginEnabled.value = enableLogin(email, password)
    }

    fun onLoginSelected(email: String, password: String, toNextScreen: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                login(email, password) { toNextScreen(it) }
            } catch (e: Exception) {
                _showErrorDialog.value = true
                _messageError.value = e.message.orEmpty()
            }
            _isLoading.value = false
        }
    }

    private suspend fun login(email: String, password: String, toNextScreen: (Boolean) -> Unit) {
        val loginResult = loginUseCase(email, password)
        when (loginResult) {
            LoginResult.Error -> {
                _showErrorDialog.value = true
                _messageError.value = "Check the data or try again."
                _isLoading.value = false
            }

            is LoginResult.Success -> {
                val isVerified = loginResult.verified
                toNextScreen(isVerified)
            }
        }
    }

    fun hideErrorDialog() {
        _showErrorDialog.value = false
    }

    private fun enableLogin(email: String, password: String) =
        validateEmail(email) && validatePassword(password)

    private fun validateEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun validatePassword(password: String) = password.length > 6
}