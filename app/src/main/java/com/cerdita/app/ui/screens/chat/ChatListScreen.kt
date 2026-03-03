package com.cerdita.app.ui.screens.chat

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
import com.cerdita.app.domain.model.Chat
import com.cerdita.app.ui.theme.PiggyPink

/**
 * 🐷 Chat List Screen - Cerdita
 * Lista de chats
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatListScreen(
    onChatClick: (String) -> Unit,
    onNavigateToContacts: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    // TODO: Obtener chats del ViewModel
    val chats by remember { mutableStateOf(emptyList<Chat>()) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chats 💕") },
                actions = {
                    IconButton(onClick = onNavigateToContacts) {
                        Icon(Icons.Default.Person, contentDescription = "Contactos")
                    }
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Ajustes")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToContacts,
                containerColor = PiggyPink
            ) {
                Icon(Icons.Default.Add, contentDescription = "Nuevo chat")
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
                        text = "No hay chats todavía",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Inicia una conversación\ncon tu persona especial 💕",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
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
 * 🐷 Chat List Item
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
            // Avatar
            Surface(
                modifier = Modifier.size(56.dp),
                shape = MaterialTheme.shapes.medium,
                color = PiggyPink
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = chat.participantPublicKey.take(2).uppercase(),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Info del chat
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = chat.participantPublicKey.take(8),
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = chat.lastMessage?.content ?: "Sin mensajes",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = if (chat.unreadCount > 0) {
                        PiggyPink
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
                    color = PiggyPink
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
