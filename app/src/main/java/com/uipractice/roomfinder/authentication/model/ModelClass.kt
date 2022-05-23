package com.uipractice.roomfinder.authentication.model

data class ErrorMessage(
    val error: String
)

data class RegisterSuccessful(
    val id: String,
    val token: String,
)

data class LoginSuccessful(
    val token: String
)

data class AuthenticationParameters(
    val email: String,
    val password: String
)