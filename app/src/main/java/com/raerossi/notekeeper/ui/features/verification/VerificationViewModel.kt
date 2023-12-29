package com.raerossi.notekeeper.ui.features.verification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raerossi.notekeeper.domain.usecases.SendEmailVerificationUseCase
import com.raerossi.notekeeper.domain.usecases.VerifyEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val sendEmailVerificationUseCase: SendEmailVerificationUseCase,
    private val verifyEmailUseCase: VerifyEmailUseCase
) : ViewModel() {

    private val _showContinueButton = MutableLiveData<Boolean>()
    val showContinueButton: LiveData<Boolean> = _showContinueButton

    init {
        viewModelScope.launch { sendEmailVerification() }
        viewModelScope.launch {
            verifyEmailUseCase()
                .catch {
                    Timber.i("Verification error: ${it.message}")
                }
                .collect { isVerified ->
                    if(isVerified) _showContinueButton.value = true
                }
        }
    }

    fun sendEmailVerification() = viewModelScope.launch { sendEmailVerificationUseCase() }
}