package com.iasiris.feature.login.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iasiris.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {
    private val userRepository = UserRepository()

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    fun onEmailChange(email: String) {
        _loginUiState.update { state ->
            state.copy(email = email)
        }
        verifyLogin()
    }

    fun onPasswordChange(password: String) {
        _loginUiState.update { state ->
            state.copy(password = password)
        }
        verifyLogin()
    }

    private fun verifyLogin() {
        val email = _loginUiState.value.email
        val password = _loginUiState.value.password

        val isEmailValid = isEmailValid(email)
        val isPasswordValid = isPasswordValid(password)

        _loginUiState.update { state ->
            state.copy(
                isLoginEnabled = isEmailValid && isPasswordValid,
                emailError = if (!isEmailValid && email.isNotEmpty()) "Email inválido" else null,
                passwordError = if (!isPasswordValid && password.isNotEmpty()) "Contraseña tiene que tener al menos 8 caracteres" else null
            )
        }
    }

    private fun isEmailValid(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isPasswordValid(password: String): Boolean = password.length >= 8

    fun onLogin(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val isValid = withContext(Dispatchers.IO) {
                val user = userRepository.getUserByEmail(_loginUiState.value.email)
                _loginUiState.value.email == user.email && _loginUiState.value.password == user.password
            }
            onResult(isValid)
        }
    }

    fun setShowRegistrationSheet(isRegisterSheetVisible: Boolean) {
        _loginUiState.update { state ->
            state.copy(isRegisterSheetVisible = isRegisterSheetVisible)
        }
    }

    fun onPasswordIconClick() {
        _loginUiState.update { state ->
            state.copy(passwordHidden = !state.passwordHidden)
        }
    }
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoginEnabled: Boolean = false,
    val isRegisterSheetVisible: Boolean = false,
    val passwordHidden: Boolean = true,
    val emailError: String? = null,
    val passwordError: String? = null
)