package com.nostrchat.app.ui.screens.contacts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nostrchat.app.domain.model.Contact

/**
 * Contacts Screen - List of all contacts
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(
    onNavigateToAddContact: () -> Unit,
    onNavigateToProfile: (String) -> Unit
) {
    val contacts by remember { mutableStateOf(emptyList<Contact>()) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contacts") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAddContact,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Contact")
            }
        }
    ) { paddingValues ->
        if (contacts.isEmpty()) {
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
                        text = "👥",
                        style = MaterialTheme.typography.displayLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Text(
                        text = "No contacts yet",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Add contacts to start chatting",
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
                items(contacts, key = { it.publicKey }) { contact ->
                    ContactItem(
                        contact = contact,
                        onClick = { onNavigateToProfile(contact.publicKey) }
                    )
                }
            }
        }
    }
}

/**
 * Contact List Item
 */
@Composable
fun ContactItem(
    contact: Contact,
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
            Surface(
                modifier = Modifier.size(48.dp),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = contact.profile?.displayName ?: contact.publicKey.take(8),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Text(
                    text = contact.profile?.name ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
