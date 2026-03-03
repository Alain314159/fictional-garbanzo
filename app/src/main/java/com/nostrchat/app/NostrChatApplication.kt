package com.nostrchat.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for NostrChat
 * Entry point for Hilt dependency injection
 */
@HiltAndroidApp
class NostrChatApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        // Initialize app-wide components here if needed
        // Hilt will automatically inject dependencies
    }
}
