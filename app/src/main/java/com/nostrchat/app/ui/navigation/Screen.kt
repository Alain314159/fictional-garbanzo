package com.nostrchat.app.ui.navigation

sealed class Screen(val route: String) {
    // Onboarding
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object CreateAccount : Screen("create_account")
    object ImportAccount : Screen("import_account")
    
    // Main
    object Main : Screen("main")
    
    // Chat
    object ChatList : Screen("chat_list")
    object ChatDetail : Screen("chat_detail/{chatId}") {
        fun createRoute(chatId: String) = "chat_detail/$chatId"
    }
    
    // Contacts
    object Contacts : Screen("contacts")
    object AddContact : Screen("add_contact")
    object Profile : Screen("profile/{pubKey}") {
        fun createRoute(pubKey: String) = "profile/$pubKey"
    }
    
    // Settings
    object Settings : Screen("settings")
    object AccountSettings : Screen("settings/account")
    object PrivacySettings : Screen("settings/privacy")
    object NotificationSettings : Screen("settings/notifications")
    object AppearanceSettings : Screen("settings/appearance")
    object RelaySettings : Screen("settings/relays")
    object AboutSettings : Screen("settings/about")
    
    // QR Scanner
    object QRScanner : Screen("qr_scanner")
}
