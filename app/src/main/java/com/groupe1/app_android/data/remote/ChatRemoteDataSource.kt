package com.groupe1.app_android.data.remote

import com.groupe1.app_android.data.mapper.mapConversationDtoToConversation
import com.groupe1.app_android.data.mapper.mapMessageDtoToMessage
import com.groupe1.app_android.data.remote.models.CreateConversationRequest
import com.groupe1.app_android.data.remote.models.ReactRequest
import com.groupe1.app_android.data.remote.models.SendMessageRequest
import com.groupe1.app_android.data.remote.services.ChatApiService
import com.groupe1.app_android.networks.NetworkModule
import retrofit2.Retrofit

class ChatRemoteDataSource(
    api: Retrofit = NetworkModule.api
) {
    // Assuming NetworkModule.api is generic, but usually we register the service in NetworkModule
    // However, ListingRemoteDataSource creates it manually. I will do same or update NetworkModule.
    // Let's create it manually to avoid circular dependencies if any, or just for consistency.
    private val chatService = api.create(ChatApiService::class.java)

    suspend fun getConversations() = chatService.getConversations().map { mapConversationDtoToConversation(it) }
    
    suspend fun getMessages(conversationId: Long) = chatService.getMessages(conversationId).map { mapMessageDtoToMessage(it) }
    
    suspend fun createConversation(otherUserId: Long) = 
        mapConversationDtoToConversation(chatService.createConversation(CreateConversationRequest(otherUserId)))

    suspend fun sendMessage(conversationId: Long, content: String) = 
        mapMessageDtoToMessage(chatService.sendMessage(conversationId, SendMessageRequest(content)))

    suspend fun reactToMessage(messageId: Long, reaction: String) {
        chatService.reactToMessage(messageId, ReactRequest(reaction))
    }
}
