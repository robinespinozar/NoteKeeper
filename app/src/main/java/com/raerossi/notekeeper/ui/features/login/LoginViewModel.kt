package com.raerossi.notekeeper.ui.features.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raerossi.notekeeper.data.remote.LoginResult
import com.raerossi.notekeeper.domain.usecases.LoginUseCase
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

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLoginEnabled.value = enableLogin(email, password)
    }

    fun onLoginSelected(
        email: String,
        password: String,
        toHome: () -> Unit,
        toVerifyEmail: () -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            val loginResult = loginUseCase(email, password)
            when (loginResult) {
                LoginResult.Error -> {
                    _showErrorDialog.value = true
                }
                is LoginResult.Success -> {
                    if (loginResult.verified) toHome() else toVerifyEmail()
                }
            }
            _isLoading.value = false
        }
    }

    private fun enableLogin(email: String, password: String) =
        validateEmail(email) && validatePassword(password)

    private fun validateEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun validatePassword(password: String) = password.length > 6
}