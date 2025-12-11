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
    // Mock Data
    private val fakeConversations = mutableListOf<com.groupe1.app_android.domain.models.Conversation>()
    private val fakeMessages = mutableMapOf<Long, MutableList<com.groupe1.app_android.domain.models.Message>>()
    private var nextMessageId = 100L
    private var nextConversationId = 100L

    init {
        // Initial Mock Data
        val conv1 = com.groupe1.app_android.domain.models.Conversation(1L, 2L, "Alice", "Hello there!", 1)
        fakeConversations.add(conv1)
        fakeMessages[1L] = mutableListOf(
            com.groupe1.app_android.domain.models.Message(1, "Hi!", 1, System.currentTimeMillis() - 10000),
            com.groupe1.app_android.domain.models.Message(2, "Hello there!", 2, System.currentTimeMillis(), mapOf("❤️" to listOf(2L)))
        )
    }

    suspend fun getConversations(): List<com.groupe1.app_android.domain.models.Conversation> {
        kotlinx.coroutines.delay(500) // Simulate network
        return fakeConversations
    }
    
    suspend fun getMessages(conversationId: Long): List<com.groupe1.app_android.domain.models.Message> {
         kotlinx.coroutines.delay(500)
         return fakeMessages[conversationId] ?: emptyList()
    }
    
    suspend fun createConversation(otherUserId: Long): com.groupe1.app_android.domain.models.Conversation {
        kotlinx.coroutines.delay(500)
        val existing = fakeConversations.find { it.otherUserId == otherUserId }
        if (existing != null) return existing
        
        val newConv = com.groupe1.app_android.domain.models.Conversation(
            id = nextConversationId++,
            otherUserId = otherUserId,
            otherUserName = "User $otherUserId", // Mock name
            lastMessage = "",
            unreadCount = 0
        )
        fakeConversations.add(newConv)
        fakeMessages[newConv.id] = mutableListOf()
        return newConv
    }

    suspend fun sendMessage(conversationId: Long, content: String): com.groupe1.app_android.domain.models.Message {
        kotlinx.coroutines.delay(300)
        val msg = com.groupe1.app_android.domain.models.Message(
            id = nextMessageId++,
            content = content,
            senderId = 1L, // Me
            timestamp = System.currentTimeMillis()
        )
        
        val list = fakeMessages.getOrPut(conversationId) { mutableListOf() }
        list.add(msg)
        
        // Update conversation last message
        val index = fakeConversations.indexOfFirst { it.id == conversationId }
        if (index != -1) {
            fakeConversations[index] = fakeConversations[index].copy(lastMessage = content)
        }
        
        // Simulate reply?
        // No, keep it simple
        return msg
    }

    suspend fun reactToMessage(messageId: Long, reaction: String) {
        kotlinx.coroutines.delay(100)
        // Find message
        fakeMessages.values.forEach { list ->
            val msgIndex = list.indexOfFirst { it.id == messageId }
            if (msgIndex != -1) {
                val msg = list[msgIndex]
                val currentReactions = msg.reactions.toMutableMap()
                val userIds = currentReactions[reaction]?.toMutableList() ?: mutableListOf()
                
                val myId = 1L // Mock My ID
                
                if (userIds.contains(myId)) {
                    userIds.remove(myId)
                    if (userIds.isEmpty()) {
                        currentReactions.remove(reaction)
                    } else {
                        currentReactions[reaction] = userIds
                    }
                } else {
                    userIds.add(myId)
                    currentReactions[reaction] = userIds
                }
                
                list[msgIndex] = msg.copy(reactions = currentReactions)
                return
            }
        }
    }
}
