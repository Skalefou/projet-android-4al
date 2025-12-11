package com.groupe1.app_android.data.repository

import com.groupe1.app_android.data.remote.ChatRemoteDataSource
import com.groupe1.app_android.domain.models.Conversation
import com.groupe1.app_android.domain.models.Message
import com.groupe1.app_android.domain.repository.ChatRepository

class ChatRepositoryImpl(
    private val remoteDataSource: ChatRemoteDataSource = ChatRemoteDataSource()
) : ChatRepository {

    override suspend fun getConversations(): List<Conversation> {
        return remoteDataSource.getConversations()
    }

    override suspend fun getMessages(conversationId: Long): List<Message> {
        return remoteDataSource.getMessages(conversationId)
    }

    override suspend fun sendMessage(conversationId: Long, content: String): Message {
        return remoteDataSource.sendMessage(conversationId, content)
    }

    override suspend fun createConversation(otherUserId: Long): Conversation {
        return remoteDataSource.createConversation(otherUserId)
    }

    override suspend fun reactToMessage(messageId: Long, reaction: String) {
        remoteDataSource.reactToMessage(messageId, reaction)
    }
}
