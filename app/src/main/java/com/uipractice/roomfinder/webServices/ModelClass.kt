package com.uipractice.roomfinder.webServices

data class ErrorMessage(
    val error: String
)

data class CreateUserValues(
    val id: String,
    val name: String,
    val job: String,
    val createdAt: String
)

data class TokenMessage(
    val token: String
)