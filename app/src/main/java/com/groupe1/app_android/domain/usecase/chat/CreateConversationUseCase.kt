package com.groupe1.app_android.domain.usecase.chat

import com.groupe1.app_android.domain.models.Conversation
import com.groupe1.app_android.domain.repository.ChatRepository

class CreateConversationUseCase(private val repository: ChatRepository) {
    suspend operator fun invoke(otherUserId: Long): Conversation = repository.createConversation(otherUserId)
}
