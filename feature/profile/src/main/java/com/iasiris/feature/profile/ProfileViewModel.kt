package com.iasiris.feature.profile

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.iasiris.core.model.User
import com.iasiris.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {
    private val userRepository = UserRepository()
    private val user = userRepository.getUserByEmail("")

    private val _profileUiState = MutableStateFlow(ProfileUiState(user))
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState

    fun onFieldChange( field: ProfileField, value:String){
        _profileUiState.update { state ->
            when(field){
                ProfileField.FullName -> state.copy(user = _profileUiState.value.user.copy(fullName = value))
                ProfileField.Nationality -> state.copy(user = _profileUiState.value.user.copy(nationality = value))
            }
        }
    }

    fun onEmailChange(email: String) {
        _profileUiState.update { state ->
            state.copy(email = email)
        }
        verifyFieldChange("email")
    }

    fun onPasswordChange(newPassword: String) {
        _profileUiState.update { state ->
            state.copy(newPassword = newPassword)
        }
        verifyFieldChange("password")
    }
    
    fun onSaveChanges(){
        //TODO chequear la logica guardar password si es que es igual a la actual y tiene al menos 8 caracteres
        val updatedUser = _profileUiState.value.user.copy( //TODO CHECK THIS LOGIC
            password = if (_profileUiState.value.newPassword.isNotEmpty()) {
                _profileUiState.value.newPassword
            } else {
                _profileUiState.value.password
                //TODO ERROR
            }
        )
        userRepository.updateUser(updatedUser)
        _profileUiState.update { state ->
            state.copy(user = updatedUser, newPassword = "")
        }
    }

    private fun verifyFieldChange(field: String) {
        if(field == "email") {
            val email = _profileUiState.value.email
            val isEmailValid = isEmailValid(email)
            _profileUiState.update { state ->
                state.copy(
                    isSaveEnabled = isEmailValid || email.isNotEmpty(),
                    emailError = if (!isEmailValid && email.isNotEmpty()) "Email inválido" else null
                )
            }

        } else if (field == "newPassword") {
            val newPassword = _profileUiState.value.newPassword
            val isNewPasswordValid = isNewPasswordValid(newPassword)
            _profileUiState.update { state ->
                state.copy(
                    isSaveEnabled = isNewPasswordValid || newPassword.isNotEmpty(),
                    passwordError = if (!isNewPasswordValid && newPassword.isNotEmpty()) "Contraseña tiene que tener al menos 8 caracteres" else null
                )
            }
        }
    }

    private fun isEmailValid(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isNewPasswordValid(newPassword: String): Boolean = newPassword.length >= 8 && _profileUiState.value.password != newPassword
}

data class ProfileUiState(
    val user: User,
    val email: String = "",
    val password: String = "",
    val newPassword: String = "",
    val isSaveEnabled: Boolean = false,
    val passwordHidden: Boolean = true,
    val emailError: String? = null,
    val passwordError: String? = null
)

enum class ProfileField {
    FullName, Nationality
}