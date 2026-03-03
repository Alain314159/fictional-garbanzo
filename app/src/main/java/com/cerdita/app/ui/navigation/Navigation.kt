package com.cerdita.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cerdita.app.ui.screens.onboarding.OnboardingScreen
import com.cerdita.app.ui.screens.onboarding.CreateProfileScreen
import com.cerdita.app.ui.screens.chat.ChatListScreen
import com.cerdita.app.ui.screens.main.MainScreen

/**
 * 🐷 Navegación Principal - Cerdita
 */
@Composable
fun CerditaNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        // Splash - Decide si mostrar onboarding o main
        composable(Screen.Splash.route) {
            // TODO: Verificar si el usuario ya tiene perfil creado
            // Por ahora, siempre muestra onboarding
            OnboardingScreen(
                onCreateProfileClick = {
                    navController.navigate(Screen.CreateProfile.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        
        // Onboarding - Bienvenida
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onCreateProfileClick = {
                    navController.navigate(Screen.CreateProfile.route)
                }
            )
        }
        
        // Crear Perfil - Generar claves
        composable(Screen.CreateProfile.route) {
            CreateProfileScreen(
                onProfileCreated = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        
        // Main - Pantalla principal con navegación inferior
        composable(Screen.Main.route) {
            MainScreen(
                navController = navController
            )
        }
        
        // Lista de Chats
        composable(Screen.ChatList.route) {
            ChatListScreen(
                onChatClick = { chatId ->
                    navController.navigate(Screen.ChatDetail.createRoute(chatId))
                },
                onNavigateToContacts = {
                    navController.navigate(Screen.Contacts.route)
                },
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }
        
        // Placeholder para otras pantallas
        composable(Screen.Contacts.route) {
            // TODO: Implementar pantalla de contactos
        }
        
        composable(Screen.Settings.route) {
            // TODO: Implementar pantalla de ajustes
        }
    }
}
