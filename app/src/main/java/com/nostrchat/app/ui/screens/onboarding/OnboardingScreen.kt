package com.nostrchat.app.ui.screens.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nostrchat.app.ui.theme.NostrOrange

/**
 * Splash Screen - Initial app screen
 */
@Composable
fun SplashScreen(
    onNavigateToOnboarding: () -> Unit,
    onNavigateToMain: () -> Unit
) {
    // TODO: Check if user has existing account
    // For now, navigate to onboarding
    onNavigateToOnboarding()
}

/**
 * Onboarding Screen - Welcome and options
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    onCreateAccount: () -> Unit,
    onImportAccount: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // App Logo/Icon
        Text(
            text = "🔓",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Title
        Text(
            text = "Welcome to NostrChat",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        // Subtitle
        Text(
            text = "Decentralized, private,\nand free messaging",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 48.dp)
        )
        
        // Features
        FeatureItem("✅ End-to-end encrypted")
        FeatureItem("🌐 No central server")
        FeatureItem("🔑 Your keys, your identity")
        FeatureItem("💸 Completely free")
        FeatureItem("🚫 Censorship resistant")
        
        Spacer(modifier = Modifier.weight(1f))
        
        // Create Account Button
        Button(
            onClick = onCreateAccount,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = NostrOrange
            )
        ) {
            Text("Create New Account")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Import Account Button
        OutlinedButton(
            onClick = onImportAccount,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Import Existing Account")
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Privacy Note
        Text(
            text = "⚠️ Your private key never leaves your device",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun FeatureItem(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = NostrOrange,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
