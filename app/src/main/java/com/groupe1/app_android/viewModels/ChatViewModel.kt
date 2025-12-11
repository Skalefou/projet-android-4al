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

    fun loadMessages(conversationId: Long) {
        viewModelScope.launch {
            _loading.value = true
            try {
                _messages.value = chatUseCases.getMessages(conversationId)
            } catch (e: Exception) {
                // handle error
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
                // handle error
            }
        }
    }
    
    fun reactToMessage(messageId: Long, reaction: String) {
        viewModelScope.launch {
             try {
                 chatUseCases.reactToMessage(messageId, reaction)
                 // Optimistic update or refresh?
                 // Simple refresh for now or optimistic update:
                 val updatedList = _messages.value.map { msg ->
                     if (msg.id == messageId) {
                         val newReactions = msg.reactions.toMutableMap()
                         newReactions[reaction] = (newReactions[reaction] ?: 0) + 1
                         msg.copy(reactions = newReactions)
                     } else msg
                 }
                 _messages.value = updatedList
             } catch(e: Exception) {
                 // handle
             }
        }
    }
    
    fun createConversation(otherUserId: Long, onCreated: (Long) -> Unit) {
        viewModelScope.launch {
            try {
               val conv = chatUseCases.createConversation(otherUserId)
               onCreated(conv.id)
            } catch (e: Exception) {
                // handle
            }
        }
    }
}
