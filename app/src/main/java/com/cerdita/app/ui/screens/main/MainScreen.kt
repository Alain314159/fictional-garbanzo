package com.cerdita.app.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.cerdita.app.ui.navigation.Screen
import com.cerdita.app.ui.screens.chat.ChatListScreen

/**
 * 🐷 Main Screen - Cerdita
 * Pantalla principal con navegación inferior
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: androidx.navigation.NavHostController
) {
    var selectedTab by remember { mutableStateOf(0) }
    
    Scaffold(
        topBar = {
            when (selectedTab) {
                0 -> MainTopBar(title = "Cerdita 💕", onSettingsClick = {
                    navController.navigate(Screen.Settings.route)
                })
                1 -> MainTopBar(title = "Contactos", onActionClick = {
                    navController.navigate(Screen.AddContact.route)
                })
                2 -> MainTopBar(title = "Ajustes")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedTab) {
                0 -> ChatListScreen(
                    onChatClick = { chatId ->
                        navController.navigate(Screen.ChatDetail.createRoute(chatId))
                    },
                    onNavigateToContacts = {
                        selectedTab = 1
                    },
                    onNavigateToSettings = {
                        selectedTab = 2
                    }
                )
                1 -> {
                    // TODO: Pantalla de Contactos
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text("Contactos - Próximamente 👥")
                    }
                }
                2 -> {
                    // TODO: Pantalla de Ajustes
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text("Ajustes - Próximamente ⚙️")
                    }
                }
            }
        }
        
        // Navegación inferior
        NavigationBar {
            NavigationBarItem(
                icon = { Icon(Icons.Default.Chat, contentDescription = "Chats") },
                label = { Text("Chats") },
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 }
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Person, contentDescription = "Contactos") },
                label = { Text("Contactos") },
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 }
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Settings, contentDescription = "Ajustes") },
                label = { Text("Ajustes") },
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 }
            )
        }
    }
}

@Composable
private fun MainTopBar(
    title: String,
    onSettingsClick: (() -> Unit)? = null,
    onActionClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = { Text(title) },
        actions = {
            if (onActionClick != null) {
                IconButton(onClick = onActionClick) {
                    Icon(Icons.Default.Person, contentDescription = "Añadir")
                }
            }
            if (onSettingsClick != null) {
                IconButton(onClick = onSettingsClick) {
                    Icon(Icons.Default.Settings, contentDescription = "Ajustes")
                }
            }
        }
    )
}
