package com.groupe1.app_android.domain.models

data class Message(
    val id: Long,
    val content: String,
    val senderId: Long,
    val timestamp: Long,
    val reactions: Map<String, List<Long>> = emptyMap() // e.g. "❤️" -> [1, 2]
)
