package com.nostrchat.app.ui.screens.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nostrchat.app.ui.theme.NostrOrange

/**
 * Import Account Screen - Import existing Nostr account
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportAccountScreen(
    onAccountImported: () -> Unit,
    onBack: () -> Unit
) {
    var privateKeyInput by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Import Account") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Text("‹")
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
                text = "📥 Import Your Account",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Text(
                text = "Enter your private key (nsec) to import your existing account",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            
            // Private Key Input
            OutlinedTextField(
                value = privateKeyInput,
                onValueChange = { privateKeyInput = it },
                label = { Text("Private Key (nsec)") },
                placeholder = { Text("nsec1...") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                ),
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
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Security Warning
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⚠️",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(end = 12.dp)
                    )
                    Text(
                        text = "Never share your private key with anyone. We only store it locally on your device.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Import Button
            Button(
                onClick = {
                    isLoading = true
                    // TODO: Import account using private key
                    // Validate and import
                    onAccountImported()
                },
                enabled = privateKeyInput.isNotBlank() && !isLoading,
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
                    Text("Import Account")
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Alternative: QR Code import
            TextButton(
                onClick = { /* TODO: Open QR scanner */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("📷 Scan QR Code instead")
            }
        }
    }
}
