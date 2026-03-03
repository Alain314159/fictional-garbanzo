package com.nostrchat.app.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Message
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.nostrchat.app.domain.model.UserProfile
import com.nostrchat.app.ui.theme.NostrOrange

/**
 * Profile Screen - View user profile
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    pubKey: String,
    onBack: () -> Unit
) {
    // TODO: Load profile from ViewModel
    val profile by remember { mutableStateOf<UserProfile?>(null) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
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
            // Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                if (profile?.banner != null) {
                    AsyncImage(
                        model = profile?.banner,
                        contentDescription = "Banner",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = NostrOrange
                    ) {}
                }
            }
            
            // Profile Picture (overlapping banner)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(MaterialTheme.shapes.medium),
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = 8.dp
                ) {
                    if (profile?.picture != null) {
                        AsyncImage(
                            model = profile?.picture,
                            contentDescription = "Profile Picture",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = profile?.displayName?.take(1)?.uppercase() ?: "?",
                                style = MaterialTheme.typography.headlineLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
            
            // Profile Info
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Display Name
                Text(
                    text = profile?.displayName ?: "Unknown",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                
                // NIP-05
                if (profile?.name != null) {
                    Text(
                        text = profile!!.name!!,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                
                // About
                if (profile?.about != null) {
                    Text(
                        text = profile!!.about!!,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )
                }
                
                // Public Key
                Text(
                    text = pubKey,
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                
                // Actions
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = { /* TODO: Start chat */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            Icons.Default.Message,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Message")
                    }
                    
                    OutlinedButton(
                        onClick = { /* TODO: Copy public key */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Copy Key")
                    }
                }
            }
        }
    }
}
