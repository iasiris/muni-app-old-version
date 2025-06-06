package com.iasiris.muniapp.view.ui.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {

    private val _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState: StateFlow<RegisterUiState> = _registerUiState

    fun onEmailChange(email: String) {
        //TODO checkear si el email ya existe, hacer con repository
        _registerUiState.update { state ->
            state.copy(email = email)
        }
        verifyRegister()
    }

    fun onFullNameChange(name: String) {
        _registerUiState.update { state ->
            state.copy(fullName = name)
        }
    }

    fun onPasswordChange(password: String) {
        _registerUiState.update { state ->
            state.copy(password = password)
        }
        verifyRegister()
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _registerUiState.update { state ->
            state.copy(confirmPassword = confirmPassword)
        }
        verifyRegister()
    }

    private fun verifyRegister() {
        val isRegisterEnabled =
            isEmailValid(_registerUiState.value.email) &&
                    isPasswordValid(_registerUiState.value.password) &&
                    isPasswordValid(_registerUiState.value.confirmPassword) &&
                    isFullNameValid(_registerUiState.value.fullName)

        _registerUiState.update { state ->
            state.copy(isRegisterEnabled = isRegisterEnabled)
        }
    }

    private fun isEmailValid(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isPasswordValid(password: String): Boolean = password.length >= 6

    private fun isFullNameValid(fullName: String): Boolean = fullName.length >= 6

    fun onRegister(): Boolean {
        var isValid = false
        if (_registerUiState.value.password == _registerUiState.value.confirmPassword) {
            isValid = true
            clearRegistrationFormAndHideSheet()
        }
        return isValid
    }

    private fun clearRegistrationFormAndHideSheet() {
        _registerUiState.update { state ->
            state.copy(
                email = "",
                password = "",
                fullName = "",
                confirmPassword = "",
                isRegisterEnabled = false,
                isSheetVisible = false
            )
        }
    }
}

data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val fullName: String = "",
    val confirmPassword: String = "",
    val isRegisterEnabled: Boolean = false,
    val isSheetVisible: Boolean = false
)