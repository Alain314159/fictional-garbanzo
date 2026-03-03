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
 * Create Account Screen - Generate new Nostr keys
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(
    onAccountCreated: () -> Unit,
    onBack: () -> Unit
) {
    var showPrivateKey by remember { mutableStateOf(false) }
    var privateKey by remember { mutableStateOf("") }
    var publicKey by remember { mutableStateOf("") }
    
    // TODO: Generate keys using Nostr SDK
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Account") },
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
                text = "🎉 Your account is ready!",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Text(
                text = "⚠️ IMPORTANT: Save your private key!",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Text(
                text = "If you lose this key, you lose your account forever. We cannot recover it for you.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            
            // Private Key Display
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Private Key (nsec)",
                            style = MaterialTheme.typography.labelLarge
                        )
                        TextButton(onClick = { showPrivateKey = !showPrivateKey }) {
                            Text(if (showPrivateKey) "HIDE" else "SHOW")
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    if (showPrivateKey) {
                        SelectionContainer {
                            Text(
                                text = privateKey.ifEmpty { "nsec1..." },
                                style = MaterialTheme.typography.bodySmall,
                                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                            )
                        }
                    } else {
                        Text(
                            text = "••••••••••••••••••••••••••••",
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    OutlinedButton(
                        onClick = { /* Copy to clipboard */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Copy to Clipboard")
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Public Key Display
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
                        text = "Public Key (npub)",
                        style = MaterialTheme.typography.labelLarge
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    SelectionContainer {
                        Text(
                            text = publicKey.ifEmpty { "npub1..." },
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    OutlinedButton(
                        onClick = { /* Copy to clipboard */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Copy to Clipboard")
                    }
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Continue Button
            Button(
                onClick = onAccountCreated,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NostrOrange
                )
            ) {
                Text("I've Saved My Key, Continue")
            }
        }
    }
}
