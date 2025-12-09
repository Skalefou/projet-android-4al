package com.groupe1.app_android.auth

import com.groupe1.app_android.models.User
import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val currentUser: User? = null,
    val accessToken: String? = null,
    val refreshToken: String? = null
)
