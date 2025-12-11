package com.groupe1.app_android.domain.usecase.chat

import com.groupe1.app_android.domain.repository.ChatRepository

class ReactToMessageUseCase(private val repository: ChatRepository) {
    suspend operator fun invoke(messageId: Long, reaction: String) = repository.reactToMessage(messageId, reaction)
}
