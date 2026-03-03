package com.cerdita.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * 🐷 Cerdita Application Class
 * La App de Chat Más Romántica del Mundo 💕
 */
@HiltAndroidApp
class CerditaApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        // Inicializar componentes de la app
        // Hilt inyectará las dependencias automáticamente
    }
}
