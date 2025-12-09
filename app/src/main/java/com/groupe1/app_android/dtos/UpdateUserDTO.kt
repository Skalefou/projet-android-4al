package com.groupe1.app_android.dtos

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserDTO(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val phone: String = "",
    val avatarUrl: String = "",
    val city: String = "",
    val isHost: Boolean = false,
    val hostDescription: String = "",
    val languages: String = "",
    val bio: String = ""
)

