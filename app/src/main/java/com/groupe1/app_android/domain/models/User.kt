package com.groupe1.app_android.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long = 0,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val phone: String = "",
    val avatarUrl: String = "",
    val city: String = "",
    val isHost: Boolean = false,
    val hostDescription: String = "",
    val languages: String = "",
    val bio: String = "",
    val ratingAverage: Double = 0.0,
    val reviewsCount: Int = 0,
    val verified: Boolean = false
)
