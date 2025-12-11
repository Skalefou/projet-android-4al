package com.groupe1.app_android.domain.models

data class Message(
    val id: Long,
    val content: String,
    val senderId: Long,
    val timestamp: Long,
    val reactions: Map<String, Int> = emptyMap() // e.g. "❤️" -> 2
)
