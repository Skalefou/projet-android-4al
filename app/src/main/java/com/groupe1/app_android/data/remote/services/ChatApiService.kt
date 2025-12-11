package com.groupe1.app_android.data.remote.services

import com.groupe1.app_android.data.remote.models.*
import retrofit2.http.*

interface ChatApiService {

    @GET("api/conversations")
    suspend fun getConversations(): List<ConversationDTO>

    @GET("api/conversations/{id}/messages")
    suspend fun getMessages(@Path("id") conversationId: Long): List<MessageDTO>

    @POST("api/conversations")
    suspend fun createConversation(@Body request: CreateConversationRequest): ConversationDTO

    @POST("api/conversations/{id}/messages")
    suspend fun sendMessage(@Path("id") conversationId: Long, @Body request: SendMessageRequest): MessageDTO

    @POST("api/messages/{id}/react")
    suspend fun reactToMessage(@Path("id") messageId: Long, @Body request: ReactRequest)
}
