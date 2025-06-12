package com.iasiris.feature.login.register

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.iasiris.core.model.User
import com.iasiris.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {
    private val userRepository = UserRepository()
    private val users = mutableListOf<User>() //TODO Agrega usuarios a lista en memoria

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
        val email = _registerUiState.value.email
        val password = _registerUiState.value.password
        val passwordConfirm = _registerUiState.value.confirmPassword

        val isEmailValid = isEmailValid(email)
        val isPasswordValid = isPasswordValid(password)
        val isPasswordConfirmValid = isPasswordValid(passwordConfirm)
        val doPasswordsMatch = password == passwordConfirm

        val isRegisterEnabled =
            isEmailValid && isPasswordValid && isPasswordConfirmValid && doPasswordsMatch && isFullNameValid(_registerUiState.value.fullName)

        _registerUiState.update { state ->
            state.copy(
                emailError = if (!isEmailValid && email.isNotEmpty()) "Email inválido" else null,
                passwordError = if (!isPasswordValid && password.isNotEmpty()) "Contraseña tiene que tener al menos 8 caracteres" else null,
                passwordConfirmError = when {
                    passwordConfirm.isEmpty() -> null
                    !isPasswordConfirmValid -> "Contraseña debe tener al menos 8 caracteres"
                    !doPasswordsMatch -> "Las contraseñas no coinciden"
                    else -> null
                },
                isRegisterEnabled = isRegisterEnabled
            )
        }
    }

    private fun isEmailValid(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isPasswordValid(password: String): Boolean = password.length >= 8

    private fun isFullNameValid(fullName: String): Boolean = fullName.length >= 8

    fun onRegister(): Boolean { // TODO agregar usuario a lista en memoria usando repo
        val canRegister = _registerUiState.value.isRegisterEnabled
        if (canRegister) {
            val newUser = User(
                email = _registerUiState.value.email,
                fullName = _registerUiState.value.fullName,
                password = _registerUiState.value.password,
                userImageUrl = ""
            )
            userRepository.addUser(newUser) //TODO agregar usuario a memoria
            Log.i("RegisterViewModel", "Usuario agregado: ${users.toString()}")
            clearRegistrationFormAndHideSheet()
        }
        return canRegister
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

    fun onPasswordIconClick() {
        _registerUiState.update { state ->
            state.copy(passwordHidden = !state.passwordHidden)
        }
    }

    fun onConfirmPasswordIconClick() {
        _registerUiState.update { state ->
            state.copy(passwordConfirmHidden = !state.passwordConfirmHidden)
        }
    }
}

data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val fullName: String = "",
    val confirmPassword: String = "",
    val isRegisterEnabled: Boolean = false,
    val isSheetVisible: Boolean = false,
    val passwordHidden: Boolean = true,
    val passwordConfirmHidden: Boolean = true,
    val emailError: String? = null,
    val passwordError: String? = null,
    val passwordConfirmError: String? = null
)