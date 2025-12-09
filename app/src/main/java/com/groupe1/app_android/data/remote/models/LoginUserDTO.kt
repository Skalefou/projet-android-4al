package com.groupe1.app_android.dtos

import kotlinx.serialization.Serializable

@Serializable
data class LoginUserDTO(
    val email: String,
    val password: String
)
