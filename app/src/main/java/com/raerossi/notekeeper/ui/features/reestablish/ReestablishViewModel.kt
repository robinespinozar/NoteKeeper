package com.raerossi.notekeeper.ui.features.reestablish

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raerossi.notekeeper.domain.usecases.RecoverPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReestablishViewModel @Inject constructor(
    private val recoverPasswordUseCase: RecoverPasswordUseCase
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _isValidEmail = MutableLiveData<Boolean>()
    val isValidEmail: LiveData<Boolean> = _isValidEmail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun recoverPassword(email: String, onSendResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            if (validateEmail(email)) sendResetPassword(email) { onSendResult(it) } else _isValidEmail.value = false
            _isLoading.value = false
        }
    }

    private suspend fun sendResetPassword(email: String, onSendResult: (Boolean) -> Unit) {
        val result = recoverPasswordUseCase(email)
        onSendResult(result)
    }

    private fun validateEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()
}