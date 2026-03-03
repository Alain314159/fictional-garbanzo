package com.nostrchat.app.ui.screens.contacts

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nostrchat.app.ui.theme.NostrOrange

/**
 * Add Contact Screen - Add new contact by npub or NIP-05
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactScreen(
    onContactAdded: () -> Unit,
    onBack: () -> Unit
) {
    var input by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Contact") },
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🔍 Find Contact",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Text(
                text = "Enter their public key (npub) or NIP-05 identifier",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            
            // Input Field
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                label = { Text("npub or NIP-05") },
                placeholder = { 
                    Text("npub1... or user@domain.com") 
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = error != null
            )
            
            if (error != null) {
                Text(
                    text = error!!,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Help Text
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "💡 Tips:",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "• npub starts with \"npub1\"",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                    Text(
                        text = "• NIP-05 looks like an email (user@domain.com)",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                    Text(
                        text = "• You can also scan a QR code",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Add Button
            Button(
                onClick = {
                    isLoading = true
                    // TODO: Search for contact
                    // onContactAdded()
                },
                enabled = input.isNotBlank() && !isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NostrOrange
                )
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Icon(
                        Icons.AutoMirrored.Filled.Send,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Add Contact")
                }
            }
        }
    }
}
