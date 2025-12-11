package com.groupe1.app_android.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupe1.app_android.domain.models.Message
import com.groupe1.app_android.domain.usecase.chat.ChatUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel(
    private val chatUseCases: ChatUseCases
) : ViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages = _messages.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun loadMessages(conversationId: Long) {
        viewModelScope.launch {
            _loading.value = true
            try {
                _messages.value = chatUseCases.getMessages(conversationId)
            } catch (e: Exception) {
            } finally {
                _loading.value = false
            }
        }
    }

    fun sendMessage(conversationId: Long, content: String) {
        viewModelScope.launch {
            try {
                val msg = chatUseCases.sendMessage(conversationId, content)
                _messages.value = _messages.value + msg
            } catch (e: Exception) {
            }
        }
    }
    
    fun reactToMessage(messageId: Long, reaction: String) {
        viewModelScope.launch {
             try {
                 chatUseCases.reactToMessage(messageId, reaction)
                 val updatedList = _messages.value.map { msg ->
                     if (msg.id == messageId) {
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
                         
                         msg.copy(reactions = currentReactions)
                     } else msg
                 }
                 _messages.value = updatedList
             } catch(e: Exception) {
             }
        }
    }
    
    fun createConversation(otherUserId: Long, onCreated: (Long) -> Unit) {
        viewModelScope.launch {
            try {
               val conv = chatUseCases.createConversation(otherUserId)
               onCreated(conv.id)
            } catch (e: Exception) {
                android.util.Log.e("ChatViewModel", "Error creating conversation", e)
                _error.value = "Erreur création: ${e.message}"
            }
        }
    }
}
