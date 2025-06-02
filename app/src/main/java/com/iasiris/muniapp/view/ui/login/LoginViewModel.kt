package com.iasiris.muniapp.view.ui.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.iasiris.muniapp.data.model.LoginData
import com.iasiris.muniapp.data.model.RegisterData

class LoginViewModel : ViewModel() {
    //TODO dejar de usar valores harcodeados
    val fakeEmail: String = "abc@abc.com"
    val fakePassword: String = "123"

    var loginState by mutableStateOf(LoginData())
        private set

    var registrationState by mutableStateOf(RegisterData())
        private set

    var showRegistrationSheet by mutableStateOf(false)
        private set

    fun onLoginEmailChange(newEmail: String) {
        loginState = loginState.copy(email = newEmail)
    }

    fun onLoginPasswordChange(newPassword: String) {
        loginState = loginState.copy(password = newPassword)
    }

    fun onShowRegistrationSheet(show: Boolean) {
        showRegistrationSheet = show
        if (!show) {
            registrationState = RegisterData()
        }
    }

    fun onRegistrationEmailChange(newEmail: String) {
        registrationState = registrationState.copy(email = newEmail)
    }

    fun onRegistrationFullNameChange(newName: String) {
        registrationState = registrationState.copy(fullName = newName)
    }

    fun onRegistrationPasswordChange(newPassword: String) {
        registrationState = registrationState.copy(password = newPassword)
    }

    fun onRegistrationConfirmPasswordChange(confirmPasswordPassword: String) {
        registrationState = registrationState.copy(confirmPassword = confirmPasswordPassword)
    }

    fun onLogin(): Boolean {
        var isValid = false
        if (loginState.email.isNotBlank() && loginState.password.isNotBlank()) {
            if (loginState.email == fakeEmail && loginState.password == fakePassword) {
                isValid = true
            }
        }
        return isValid
    }

    fun onRegister(): Boolean {
        var isValid = false
        if (registrationState.email.isNotBlank() &&
            registrationState.fullName.isNotBlank() &&
            registrationState.password.isNotBlank() &&
            registrationState.confirmPassword.isNotBlank()
        ) {
            if (registrationState.password == registrationState.confirmPassword) {
                isValid = true
            }
        }
        clearRegistrationFormAndHideSheet()
        return isValid
    }

    private fun clearRegistrationFormAndHideSheet() {
        registrationState = RegisterData()
        onShowRegistrationSheet(false)
    }

}