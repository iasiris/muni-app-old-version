package com.iasiris.muniapp.data.model

data class LoginData(
    val email: String = "",
    val password: String = ""
)

data class RegisterData(
    val email: String = "",
    val password: String = "",
    val fullName: String = "",
    val confirmPassword: String = ""
)