package com.raerossi.notekeeper.ui.features.registration

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
) : ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _confirmedPassword = MutableLiveData<String>()
    val confirmedPassword: LiveData<String> = _confirmedPassword

    private val _isRegistrationEnabled = MutableLiveData<Boolean>()
    val isRegistrationEnabled: LiveData<Boolean> = _isRegistrationEnabled

    private val _isPasswordsMatch = MutableLiveData<Boolean>()
    val isPasswordsMatch: LiveData<Boolean> = _isPasswordsMatch

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onSignUpChanged(name: String, email: String, password: String, confirmedPassword: String) {
        _name.value = name
        _email.value = email
        _password.value = password
        _confirmedPassword.value = confirmedPassword
        _isRegistrationEnabled.value = enableSignUp(name, email, password, confirmedPassword)
    }

    fun onSignUpSelected(password: String, confirmedPassword: String) {
        _isPasswordsMatch.value = validatePasswordMatches(password, confirmedPassword)
    }

    private fun enableSignUp(name: String,email: String,password: String,confirmedPassword: String): Boolean {
        return validateName(name) && validateEmail(email) && validatePassword(password) && validatePassword(confirmedPassword)
    }

    private fun validateName(name: String) = name.matches(Regex("^[a-zA-Z ]+\$")) && name.isNotEmpty()

    private fun validateEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun validatePassword(password: String) = password.length > 6

    private fun validatePasswordMatches(password: String, confirmedPassword: String) = (password == confirmedPassword)
}
