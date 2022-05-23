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

data class ObserverValue(
    val isSuccess: Boolean,
    val message: String
)