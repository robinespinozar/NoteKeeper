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

    private val _isValidName = MutableLiveData<Boolean>()
    val isValidName: LiveData<Boolean> = _isValidName

    private val _isValidEmail = MutableLiveData<Boolean>()
    val isValidEmail: LiveData<Boolean> = _isValidEmail

    private val _isValidPassword = MutableLiveData<Boolean>()
    val isValidPassword: LiveData<Boolean> = _isValidPassword

    private val _isPasswordsMatch = MutableLiveData<Boolean>()
    val isPasswordsMatch: LiveData<Boolean> = _isPasswordsMatch

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onSignUpChanged(name: String, email: String, password: String, confirmedPassword: String) {
        _name.value = name
        _email.value = email
        _password.value = password
        _confirmedPassword.value = confirmedPassword
    }

    fun onSignUpSelected(name: String, email: String, password: String, confirmedPassword: String) {
        _isValidName.value = validateName(name)
        _isValidEmail.value = validateEmail(email)
        _isValidPassword.value = validatePassword(password)
        _isPasswordsMatch.value = validatePasswordMatches(password, confirmedPassword)
    }

    private fun validateName(name: String) = name.matches(Regex("^[a-zA-Z ]+\$")) && name.isNotEmpty()

    private fun validateEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun validatePassword(password: String) = password.length > 6

    private fun validatePasswordMatches(password: String, confirmedPassword: String) = password == confirmedPassword
}
