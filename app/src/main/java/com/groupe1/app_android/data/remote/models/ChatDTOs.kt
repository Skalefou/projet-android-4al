package com.groupe1.app_android.data.remote.models

import com.google.gson.annotations.SerializedName

data class ConversationDTO(
    @SerializedName("id") val id: Long,
    @SerializedName("other_user_id") val otherUserId: Long,
    @SerializedName("other_user_name") val otherUserName: String,
    @SerializedName("last_message") val lastMessage: String,
    @SerializedName("unread_count") val unreadCount: Int = 0
)

data class MessageDTO(
    @SerializedName("id") val id: Long,
    @SerializedName("content") val content: String,
    @SerializedName("sender_id") val senderId: Long,
    @SerializedName("timestamp") val timestamp: Long,
    @SerializedName("reactions") val reactions: Map<String, Int> = emptyMap()
)

data class CreateConversationRequest(
    @SerializedName("other_user_id") val otherUserId: Long
)

data class SendMessageRequest(
    @SerializedName("content") val content: String
)

data class ReactRequest(
    @SerializedName("reaction") val reaction: String
)
