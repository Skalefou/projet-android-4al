package com.groupe1.app_android.auth

import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val token: String? = null
)
