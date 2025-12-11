package com.groupe1.app_android.ui.chat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.groupe1.app_android.domain.models.Message
import com.groupe1.app_android.viewModels.ChatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    viewModel: ChatViewModel,
    conversationId: Long,
    onBack: () -> Unit,
    initialMessage: String? = null
) {
    LaunchedEffect(conversationId) {
        viewModel.loadMessages(conversationId)
    }

    val messages by viewModel.messages.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsStateWithLifecycle()
    var inputText by remember { mutableStateOf(initialMessage ?: "") }
    var selectedMessageId by remember { mutableStateOf<Long?>(null) }
    var showReactionDialog by remember { mutableStateOf(false) }

    if (showReactionDialog && selectedMessageId != null) {
        ReactionDialog(
            onDismiss = { showReactionDialog = false },
            onReact = { reaction ->
                viewModel.reactToMessage(selectedMessageId!!, reaction)
                showReactionDialog = false
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Conversation") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding).fillMaxSize()) {
        if (loading) {
            LinearProgressIndicator(Modifier.fillMaxWidth())
        }

        LazyColumn(
            modifier = Modifier.weight(1f).padding(horizontal = 16.dp),
            reverseLayout = true
        ) {
            items(messages.reversed()) { msg ->
                MessageItem(
                    message = msg,
                    isMe = msg.senderId == 1L,
                    onLongClick = {
                        selectedMessageId = msg.id
                        showReactionDialog = true
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Row(
            Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Votre message...") }
            )
            IconButton(onClick = {
                if (inputText.isNotBlank()) {
                    viewModel.sendMessage(conversationId, inputText)
                    inputText = ""
                }
            }) {
                Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Envoyer")
            }
        }
    }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MessageItem(message: Message, isMe: Boolean, onLongClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isMe) Alignment.End else Alignment.Start
    ) {
        Surface(
            color = if (isMe) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.combinedClickable(onLongClick = onLongClick, onClick = {})
        ) {
            Text(
                message.content,
                modifier = Modifier.padding(12.dp)
            )
        }
        if (message.reactions.isNotEmpty()) {
            Row {
                message.reactions.forEach { (reaction, userIds) ->
                    val count = userIds.size
                    val isReactedByMe = userIds.contains(1L)
                    val style = if (isReactedByMe) MaterialTheme.typography.labelLarge else MaterialTheme.typography.labelSmall
                    Text("$reaction $count", style = style, modifier = Modifier.padding(end = 4.dp))
                }
            }
        }
    }
}

@Composable
fun ReactionDialog(onDismiss: () -> Unit, onReact: (String) -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Réagir") },
        text = {
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                listOf("❤️", "😂", "👍", "👎").forEach { emoji ->
                    TextButton(onClick = { onReact(emoji) }) {
                        Text(emoji, style = MaterialTheme.typography.titleLarge)
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = { TextButton(onClick = onDismiss) { Text("Annuler") } }
    )
}
