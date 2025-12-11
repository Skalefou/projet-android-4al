package com.groupe1.app_android.domain.repository

import com.groupe1.app_android.domain.models.Conversation
import com.groupe1.app_android.domain.models.Message

interface ChatRepository {
    suspend fun getConversations(): List<Conversation>
    suspend fun getMessages(conversationId: Long): List<Message>
    suspend fun sendMessage(conversationId: Long, content: String): Message
    suspend fun createConversation(otherUserId: Long): Conversation
    suspend fun reactToMessage(messageId: Long, reaction: String)
}
