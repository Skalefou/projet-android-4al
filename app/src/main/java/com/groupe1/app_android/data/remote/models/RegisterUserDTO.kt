package com.groupe1.app_android.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserDTO(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)