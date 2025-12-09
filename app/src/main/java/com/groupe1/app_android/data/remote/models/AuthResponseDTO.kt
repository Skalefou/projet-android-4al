package com.groupe1.app_android.dtos

import com.groupe1.app_android.domain.models.User
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDTO(
    val user: User,
    val tokenPair: TokenPairDTO
)
