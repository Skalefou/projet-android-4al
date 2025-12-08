package com.groupe1.app_android.dtos

import kotlinx.serialization.Serializable

@Serializable
data class TokenPairDTO(
    val access: String,
    val refresh: String
)
