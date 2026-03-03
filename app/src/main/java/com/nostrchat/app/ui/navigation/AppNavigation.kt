package com.nostrchat.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nostrchat.app.ui.screens.chat.ChatDetailScreen
import com.nostrchat.app.ui.screens.chat.ChatListScreen
import com.nostrchat.app.ui.screens.contacts.AddContactScreen
import com.nostrchat.app.ui.screens.contacts.ContactsScreen
import com.nostrchat.app.ui.screens.onboarding.CreateAccountScreen
import com.nostrchat.app.ui.screens.onboarding.ImportAccountScreen
import com.nostrchat.app.ui.screens.onboarding.OnboardingScreen
import com.nostrchat.app.ui.screens.onboarding.SplashScreen
import com.nostrchat.app.ui.screens.profile.ProfileScreen
import com.nostrchat.app.ui.screens.settings.SettingsScreen

/**
 * App Navigation Graph
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        // Onboarding Flow
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToOnboarding = {
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToMain = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onCreateAccount = {
                    navController.navigate(Screen.CreateAccount.route)
                },
                onImportAccount = {
                    navController.navigate(Screen.ImportAccount.route)
                }
            )
        }
        
        composable(Screen.CreateAccount.route) {
            CreateAccountScreen(
                onAccountCreated = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable(Screen.ImportAccount.route) {
            ImportAccountScreen(
                onAccountImported = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        
        // Main App Flow
        composable(Screen.Main.route) {
            MainScreen(
                navController = navController
            )
        }
        
        // Chat Detail
        composable(
            route = Screen.ChatDetail.route,
            arguments = listOf(
                navArgument("chatId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val chatId = backStackEntry.arguments?.getString("chatId") ?: return@composable
            ChatDetailScreen(
                chatId = chatId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        
        // Contacts
        composable(Screen.Contacts.route) {
            ContactsScreen(
                onNavigateToAddContact = {
                    navController.navigate(Screen.AddContact.route)
                },
                onNavigateToProfile = { pubKey ->
                    navController.navigate(Screen.Profile.createRoute(pubKey))
                }
            )
        }
        
        composable(Screen.AddContact.route) {
            AddContactScreen(
                onContactAdded = {
                    navController.popBackStack()
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        
        // Profile
        composable(
            route = Screen.Profile.route,
            arguments = listOf(
                navArgument("pubKey") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val pubKey = backStackEntry.arguments?.getString("pubKey") ?: return@composable
            ProfileScreen(
                pubKey = pubKey,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        
        // Settings
        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateToAccountSettings = {
                    navController.navigate(Screen.AccountSettings.route)
                },
                onNavigateToPrivacySettings = {
                    navController.navigate(Screen.PrivacySettings.route)
                },
                onNavigateToNotificationSettings = {
                    navController.navigate(Screen.NotificationSettings.route)
                },
                onNavigateToAppearanceSettings = {
                    navController.navigate(Screen.AppearanceSettings.route)
                },
                onNavigateToRelaySettings = {
                    navController.navigate(Screen.RelaySettings.route)
                },
                onNavigateToAboutSettings = {
                    navController.navigate(Screen.AboutSettings.route)
                }
            )
        }
        
        // Placeholder for settings sub-screens
        composable(Screen.AccountSettings.route) {
            SettingsScreen(onNavigateToAccountSettings = {}, onNavigateToPrivacySettings = {}, onNavigateToNotificationSettings = {}, onNavigateToAppearanceSettings = {}, onNavigateToRelaySettings = {}, onNavigateToAboutSettings = {})
        }
        composable(Screen.PrivacySettings.route) {
            SettingsScreen(onNavigateToAccountSettings = {}, onNavigateToPrivacySettings = {}, onNavigateToNotificationSettings = {}, onNavigateToAppearanceSettings = {}, onNavigateToRelaySettings = {}, onNavigateToAboutSettings = {})
        }
        composable(Screen.NotificationSettings.route) {
            SettingsScreen(onNavigateToAccountSettings = {}, onNavigateToPrivacySettings = {}, onNavigateToNotificationSettings = {}, onNavigateToAppearanceSettings = {}, onNavigateToRelaySettings = {}, onNavigateToAboutSettings = {})
        }
        composable(Screen.AppearanceSettings.route) {
            SettingsScreen(onNavigateToAccountSettings = {}, onNavigateToPrivacySettings = {}, onNavigateToNotificationSettings = {}, onNavigateToAppearanceSettings = {}, onNavigateToRelaySettings = {}, onNavigateToAboutSettings = {})
        }
        composable(Screen.RelaySettings.route) {
            SettingsScreen(onNavigateToAccountSettings = {}, onNavigateToPrivacySettings = {}, onNavigateToNotificationSettings = {}, onNavigateToAppearanceSettings = {}, onNavigateToRelaySettings = {}, onNavigateToAboutSettings = {})
        }
        composable(Screen.AboutSettings.route) {
            SettingsScreen(onNavigateToAccountSettings = {}, onNavigateToPrivacySettings = {}, onNavigateToNotificationSettings = {}, onNavigateToAppearanceSettings = {}, onNavigateToRelaySettings = {}, onNavigateToAboutSettings = {})
        }
    }
}

/**
 * Main Screen with Bottom Navigation
 */
@Composable
fun MainScreen(navController: NavHostController) {
    // TODO: Implement main screen with bottom navigation
    // For now, just navigate to chat list
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
