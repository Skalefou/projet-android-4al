package com.groupe1.app_android.domain.usecase.chat

import com.groupe1.app_android.domain.models.Message
import com.groupe1.app_android.domain.repository.ChatRepository

class GetMessagesUseCase(private val repository: ChatRepository) {
    suspend operator fun invoke(conversationId: Long): List<Message> = repository.getMessages(conversationId)
}
