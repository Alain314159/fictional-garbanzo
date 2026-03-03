package com.cerdita.app.ui.navigation

/**
 * 🐷 Screens - Cerdita
 * Navegación de la app
 */
sealed class Screen(val route: String) {
    // Onboarding
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object CreateProfile : Screen("create_profile")
    
    // Main
    object Main : Screen("main")
    
    // Chat
    object ChatList : Screen("chat_list")
    object ChatDetail : Screen("chat_detail/{chatId}") {
        fun createRoute(chatId: String) = "chat_detail/$chatId"
    }
    
    // Contactos
    object Contacts : Screen("contacts")
    object AddContact : Screen("add_contact")
    
    // Perfil
    object Profile : Screen("profile")
    
    // Ajustes
    object Settings : Screen("settings")
    object ThemeSelector : Screen("settings/theme")
}
