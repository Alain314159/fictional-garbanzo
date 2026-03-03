package com.nostrchat.app.ui.screens.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nostrchat.app.domain.model.Chat
import com.nostrchat.app.domain.model.Message

/**
 * Chat List Screen - Main screen showing all conversations
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatListScreen(
    onChatClick: (String) -> Unit,
    onNavigateToContacts: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    // TODO: Get chats from ViewModel
    val chats by remember { mutableStateOf(emptyList<Chat>()) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chats") },
                actions = {
                    IconButton(onClick = onNavigateToContacts) {
                        Icon(Icons.Default.Person, contentDescription = "Contacts")
                    }
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToContacts,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "New Chat")
            }
        }
    ) { paddingValues ->
        if (chats.isEmpty()) {
            // Empty State
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "💬",
                        style = MaterialTheme.typography.displayLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Text(
                        text = "No chats yet",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Start a conversation to see it here",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(chats, key = { it.id }) { chat ->
                    ChatListItem(
                        chat = chat,
                        onClick = { onChatClick(chat.id) }
                    )
                }
            }
        }
    }
}

/**
 * Chat List Item
 */
@Composable
fun ChatListItem(
    chat: Chat,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar placeholder
            Surface(
                modifier = Modifier.size(56.dp),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = chat.participants.firstOrNull()?.take(2)?.uppercase() ?: "?",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Chat info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = chat.participants.firstOrNull() ?: "Unknown",
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    
                    Text(
                        text = formatTime(chat.lastMessageTime),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = chat.lastMessage?.content ?: "No messages yet",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = if (chat.unreadCount > 0) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            }
            
            // Unread badge
            if (chat.unreadCount > 0) {
                Spacer(modifier = Modifier.width(8.dp))
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Box(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = chat.unreadCount.toString(),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}

private fun formatTime(timestamp: Long): String {
    if (timestamp == 0L) return ""
    val now = System.currentTimeMillis()
    val diff = now - timestamp
    return when {
        diff < 60000 -> "now"
        diff < 3600000 -> "${diff / 60000}m"
        diff < 86400000 -> "${diff / 3600000}h"
        else -> "${diff / 86400000}d"
    }
}
