package com.groupe1.app_android.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long = 0,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = ""
)
