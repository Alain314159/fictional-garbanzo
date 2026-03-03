package com.cerdita.app.ui.screens.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cerdita.app.domain.model.AvatarType
import com.cerdita.app.ui.theme.PiggyPink
import kotlinx.coroutines.launch

/**
 * 🐷 Create Profile Screen - Cerdita
 * Pantalla para crear perfil con avatar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProfileScreen(
    onProfileCreated: () -> Unit,
    onBack: () -> Unit
) {
    val viewModel: CreateProfileViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    
    // Escuchar eventos
    LaunchedEffect(state) {
        viewModel.events.collect { event ->
            when (event) {
                is CreateProfileEvent.ProfileCreatedSuccessfully -> {
                    onProfileCreated()
                }
                is CreateProfileEvent.ProfileCreationFailed -> {
                    // Mostrar error
                }
                null -> {}
            }
        }
    }
    
    var username by remember { mutableStateOf("") }
    var selectedAvatar by remember { mutableStateOf(AvatarType.PIGGY) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear Perfil 💕") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
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
            // Icono
            Text(
                text = "🐷🤗🐨",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            // Título
            Text(
                text = "¡Crea tu perfil!",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Text(
                text = "Elige tu nombre y avatar",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            
            // Input de nombre
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Nombre de usuario") },
                placeholder = { Text("Tu nombre o apodo cariñoso") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = username.length > 20,
                supportingText = {
                    if (username.length > 20) {
                        Text("Nombre demasiado largo")
                    } else {
                        Text("${username.length}/20")
                    }
                }
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Selector de Avatar
            Text(
                text = "Elige tu avatar:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Cerdita 🐷
                AvatarOption(
                    emoji = "🐷",
                    label = "Cerdita",
                    isSelected = selectedAvatar == AvatarType.PIGGY,
                    onClick = { selectedAvatar = AvatarType.PIGGY }
                )
                
                // Koalita 🐨
                AvatarOption(
                    emoji = "🐨",
                    label = "Koalita",
                    isSelected = selectedAvatar == AvatarType.KOALA,
                    onClick = { selectedAvatar = AvatarType.KOALA }
                )
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Advertencia
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
                        text = "⚠️ Importante:",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Tu clave privada se guardará automáticamente en tu dispositivo. ¡Nunca la compartas!",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Botón Crear Perfil
            Button(
                onClick = {
                    viewModel.createProfile(
                        displayName = username,
                        avatarType = selectedAvatar
                    )
                },
                enabled = username.isNotBlank() && !state.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PiggyPink
                )
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Creando perfil...")
                } else {
                    Text("✨ Crear Perfil")
                }
            }
            
            // Mostrar clave pública si fue creada
            if (state.profileCreated && state.generatedKeyPair != null) {
                Spacer(modifier = Modifier.height(16.dp))
                
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = PiggyPink.copy(alpha = 0.1f)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "✅ ¡Perfil creado!",
                            style = MaterialTheme.typography.titleMedium,
                            color = PiggyPink
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Tu clave pública:",
                            style = MaterialTheme.typography.labelLarge
                        )
                        Text(
                            text = state.generatedKeyPair!!.getShortNpub(),
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                        )
                    }
                }
            }
            
            // Mostrar error si existe
            state.error?.let { error ->
                Spacer(modifier = Modifier.height(16.dp))
                
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Text(
                        text = "❌ $error",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun AvatarOption(
    emoji: String,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.width(120.dp),
        border = if (isSelected) {
            BorderStroke(2.dp, PiggyPink)
        } else {
            null
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = emoji,
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}
