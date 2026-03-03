package com.nostrchat.app.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Settings Screen - Main settings menu
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateToAccountSettings: () -> Unit,
    onNavigateToPrivacySettings: () -> Unit,
    onNavigateToNotificationSettings: () -> Unit,
    onNavigateToAppearanceSettings: () -> Unit,
    onNavigateToRelaySettings: () -> Unit,
    onNavigateToAboutSettings: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SettingsItem(
                icon = Icons.Default.Person,
                title = "Account",
                subtitle = "Manage your account and keys",
                onClick = onNavigateToAccountSettings
            )
            
            SettingsItem(
                icon = Icons.Default.Lock,
                title = "Privacy",
                subtitle = "Privacy and security settings",
                onClick = onNavigateToPrivacySettings
            )
            
            SettingsItem(
                icon = Icons.Default.Notifications,
                title = "Notifications",
                subtitle = "Notification preferences",
                onClick = onNavigateToNotificationSettings
            )
            
            SettingsItem(
                icon = Icons.Default.Palette,
                title = "Appearance",
                subtitle = "Theme and display settings",
                onClick = onNavigateToAppearanceSettings
            )
            
            SettingsItem(
                icon = Icons.Default.Dns,
                title = "Relays",
                subtitle = "Manage relay connections",
                onClick = onNavigateToRelaySettings
            )
            
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            
            SettingsItem(
                icon = Icons.Default.Info,
                title = "About NostrChat",
                subtitle = "Version, licenses, and more",
                onClick = onNavigateToAboutSettings
            )
        }
    }
}

@Composable
fun SettingsItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
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
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
