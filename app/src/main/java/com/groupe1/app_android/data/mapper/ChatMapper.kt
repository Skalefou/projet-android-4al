package com.groupe1.app_android.data.mapper

import com.groupe1.app_android.data.remote.models.ConversationDTO
import com.groupe1.app_android.data.remote.models.MessageDTO
import com.groupe1.app_android.domain.models.Conversation
import com.groupe1.app_android.domain.models.Message

fun mapConversationDtoToConversation(dto: ConversationDTO): Conversation {
    return Conversation(
        id = dto.id,
        otherUserId = dto.otherUserId,
        otherUserName = dto.otherUserName,
        lastMessage = dto.lastMessage,
        unreadCount = dto.unreadCount
    )
}

fun mapMessageDtoToMessage(dto: MessageDTO): Message {
    return Message(
        id = dto.id,
        content = dto.content,
        senderId = dto.senderId,
        timestamp = dto.timestamp,
        reactions = dto.reactions
    )
}
