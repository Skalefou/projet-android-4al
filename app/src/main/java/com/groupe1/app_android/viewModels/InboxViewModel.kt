package com.groupe1.app_android.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groupe1.app_android.domain.models.Conversation
import com.groupe1.app_android.domain.usecase.chat.ChatUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InboxViewModel(
    private val chatUseCases: ChatUseCases
) : ViewModel() {

    private val _conversations = MutableStateFlow<List<Conversation>>(emptyList())
    val conversations = _conversations.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        loadConversations()
    }

    fun loadConversations() {
        viewModelScope.launch {
            _loading.value = true
            try {
                _conversations.value = chatUseCases.getConversations()
            } catch (e: Exception) {
                _error.value = "Erreur de chargement: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }
}
