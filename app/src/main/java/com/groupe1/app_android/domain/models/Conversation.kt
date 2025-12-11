package com.groupe1.app_android.domain.models

data class Conversation(
    val id: Long,
    val otherUserId: Long,
    val otherUserName: String,
    val lastMessage: String,
    val unreadCount: Int = 0
)
