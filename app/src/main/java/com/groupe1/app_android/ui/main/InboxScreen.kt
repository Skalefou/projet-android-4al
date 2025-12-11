package com.groupe1.app_android.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.groupe1.app_android.domain.models.Conversation
import com.groupe1.app_android.viewModels.InboxViewModel
import com.groupe1.app_android.viewModels.ChatViewModel

@Composable
fun InboxScreen(
    viewModel: InboxViewModel,
    chatViewModel: ChatViewModel, // Need separate view model or just callback? Passed for creating conv. Or use Callback.
    // Better to use callback for navigation, but for creating conv?
    // Let's assume we navigate to ChatScreen with a new ID or existing one.
    // But createConversation returns an ID.
    onNavigateToChat: (Long) -> Unit
) {
    val conversations by viewModel.conversations.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsStateWithLifecycle()
    val error by chatViewModel.error.collectAsStateWithLifecycle()
    val context = androidx.compose.ui.platform.LocalContext.current
    var showCreateDialog by remember { mutableStateOf(false) }

    LaunchedEffect(error) {
        error?.let {
            android.widget.Toast.makeText(context, it, android.widget.Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadConversations()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showCreateDialog = true }) {
                Icon(Icons.Default.Add, "Nouvelle conversation")
            }
        }
    ) { padding ->
        Box(Modifier.padding(padding).fillMaxSize()) {
           if (loading) {
               CircularProgressIndicator(Modifier.align(Alignment.Center))
           } else if (conversations.isEmpty()) {
               Text("Aucune conversation", Modifier.align(Alignment.Center))
           } else {
               LazyColumn {
                   items(conversations) { conv ->
                       ListItem(
                           headlineContent = { Text(conv.otherUserName) },
                           supportingContent = { Text(conv.lastMessage) },
                           modifier = Modifier.clickable { onNavigateToChat(conv.id) }
                       )
                       HorizontalDivider()
                   }
               }
           }
        }
    }

    if (showCreateDialog) {
        var otherUserIdText by remember { mutableStateOf("") }
        AlertDialog(
            onDismissRequest = { showCreateDialog = false },
            title = { Text("Nouvelle conversation") },
            text = {
                Column {
                    OutlinedTextField(
                        value = otherUserIdText,
                        onValueChange = { otherUserIdText = it },
                        label = { Text("ID de l'utilisateur") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = otherUserIdText.isNotEmpty() && otherUserIdText.toLongOrNull() == null,
                        supportingText = { if (otherUserIdText.isNotEmpty() && otherUserIdText.toLongOrNull() == null) Text("Entrez un nombre valid") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    val id = otherUserIdText.toLongOrNull()
                    if (id != null) {
                         chatViewModel.createConversation(id) { newId ->
                             showCreateDialog = false
                             onNavigateToChat(newId)
                         }
                    }
                }) {
                    Text("Créer")
                }
            },
            dismissButton = {
                TextButton(onClick = { showCreateDialog = false }) { Text("Annuler") }
            }
        )
    }
}
