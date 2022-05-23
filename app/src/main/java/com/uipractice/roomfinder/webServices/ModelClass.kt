package com.uipractice.roomfinder.webServices

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

data class RegisterBody(
    val email: String,
    val password: String
)