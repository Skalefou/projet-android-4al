package com.groupe1.app_android.dtos

import kotlinx.serialization.Serializable

@Serializable
data class TokenPairDTO(
    val accessToken: String,
    val refreshToken: String
)
