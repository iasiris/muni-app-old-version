package com.iasiris.muniapp.view.ui.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    //TODO dejar de usar valores harcodeados
    val fakeEmail: String = "a@a.com"
    val fakePassword: String = "123456"

    //el que accede y modifica el viewModel
    private val _loginUiState = MutableStateFlow(LoginUiState())

    //lo escucha los composables
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    fun onEmailChange(email: String) {
        _loginUiState.update { state ->
            state.copy(email = email)
        }
        verifyLogin()
    }

    fun onPasswordChange(password: String) {
        _loginUiState.update { state -> // 'update' is thread-safe
            state.copy(password = password)
        }
        verifyLogin()
    }

    private fun verifyLogin() {
        val enabledLogin =
            isEmailValid(_loginUiState.value.email) && isPasswordValid(_loginUiState.value.password)
        _loginUiState.update { state ->
            state.copy(isLoginEnabled = enabledLogin)
        }
    }

    private fun isEmailValid(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isPasswordValid(password: String): Boolean = password.length >= 6

    fun onLogin(): Boolean {
        var isValid = false
        if (_loginUiState.value.email == fakeEmail && _loginUiState.value.password == fakePassword) {
            isValid = true
        }
        return isValid
    }

    fun setShowRegistrationSheet(isRegisterSheetVisible: Boolean) {
        _loginUiState.update { state ->
            state.copy(isRegisterSheetVisible = isRegisterSheetVisible)
        }
    }
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoginEnabled: Boolean = false,
    val isRegisterSheetVisible: Boolean = false
)