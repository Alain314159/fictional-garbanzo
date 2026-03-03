package com.cerdita.app.ui.screens.onboarding

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
import com.cerdita.app.ui.theme.PiggyPink

/**
 * 🐷 Onboarding Screen - Cerdita
 * Pantalla de bienvenida
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    onCreateProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icono/Logo
        Text(
            text = "🐷💕🐨",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Título
        Text(
            text = "Bienvenido a Cerdita 💕",
            style = MaterialTheme.typography.headlineLarge,
            color = PiggyPink,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        // Subtítulo
        Text(
            text = "La app de chat más romántica\n🐷🤗🐨",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 48.dp)
        )
        
        // Características
        FeatureItem("💌 Mensajes que cobran vida")
        FeatureItem("✨ Efectos románticos especiales")
        FeatureItem("🔒 100% privado y encriptado")
        FeatureItem("🐷🐨 Cerdita y Koalita te acompañan")
        
        Spacer(modifier = Modifier.weight(1f))
        
        // Botón Crear Perfil
        Button(
            onClick = onCreateProfileClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PiggyPink
            )
        ) {
            Text("Comenzar 💕")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Nota de privacidad
        Text(
            text = "🔒 Tus claves nunca salen de tu dispositivo",
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
            tint = PiggyPink,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
