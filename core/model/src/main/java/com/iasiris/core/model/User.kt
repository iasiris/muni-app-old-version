package com.iasiris.core.model

data class User(
    val email: String,
    val password: String,
    val fullName: String,
    val userImageUrl: String,
    val nationality: String = "",
)