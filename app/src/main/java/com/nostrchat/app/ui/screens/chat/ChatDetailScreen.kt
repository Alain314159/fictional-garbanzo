package com.nostrchat.app.ui.screens.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nostrchat.app.domain.model.Message

/**
 * Chat Detail Screen - Individual conversation
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatDetailScreen(
    chatId: String,
    onBack: () -> Unit
) {
    var messageText by remember { mutableStateOf("") }
    val messages by remember { mutableStateOf(emptyList<Message>()) }
    val listState = rememberLazyListState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat") }, // TODO: Show contact name
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Messages List
            LazyColumn(
                modifier = Modifier.weight(1f),
                state = listState,
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(messages, key = { it.id }) { message ->
                    MessageBubble(message = message)
                }
            }
            
            Divider()
            
            // Message Input
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                OutlinedTextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier
                        .weight(1f)
                        .defaultMinSize(minHeight = 56.dp),
                    placeholder = { Text("Type a message…") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary
                    ),
                    maxLines = 4
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                IconButton(
                    onClick = {
                        // TODO: Send message
                        messageText = ""
                    },
                    enabled = messageText.isNotBlank(),
                    modifier = Modifier.size(56.dp)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Send",
                        tint = if (messageText.isNotBlank()) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                }
            }
            
            // Encryption indicator
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "End-to-end encrypted",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

/**
 * Message Bubble Component
 */
@Composable
fun MessageBubble(
    message: Message
) {
    val isFromMe = false // TODO: Check if message is from current user
    
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isFromMe) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            shape = if (isFromMe) {
                MaterialTheme.shapes.medium.copy(
                    topEnd = MaterialTheme.shapes.small.topEnd
                )
            } else {
                MaterialTheme.shapes.medium.copy(
                    topStart = MaterialTheme.shapes.small.topStart
                )
            },
            color = if (isFromMe) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            },
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = message.content,
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (isFromMe) {
                        MaterialTheme.colorScheme.onPrimary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = formatMessageTime(message.timestamp),
                        style = MaterialTheme.typography.labelSmall,
                        color = if (isFromMe) {
                            MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                }
            }
        }
    }
}

private fun formatMessageTime(timestamp: Long): String {
    val hour = (timestamp / 3600000) % 24
    val minute = (timestamp / 60000) % 60
    return String.format("%02d:%02d", hour, minute)
}
