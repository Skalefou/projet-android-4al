package com.groupe1.app_android.domain.usecase.chat

data class ChatUseCases(
    val getConversations: GetConversationsUseCase,
    val getMessages: GetMessagesUseCase,
    val sendMessage: SendMessageUseCase,
    val createConversation: CreateConversationUseCase,
    val reactToMessage: ReactToMessageUseCase
)
