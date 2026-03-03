package com.cerdita.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.cerdita.app.ui.navigation.CerditaNavigation
import com.cerdita.app.ui.theme.CerditaTheme
import com.cerdita.app.ui.theme.PiggyColorScheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * 🐷 MainActivity - Cerdita
 * La App de Chat Más Romántica del Mundo 💕
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            // Usar tema Cerdita (por defecto Piggy)
            // En el futuro, esto leerá el tema guardado del usuario
            MaterialTheme(
                colorScheme = PiggyColorScheme,
                typography = CerditaTypography
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CerditaNavigation()
                }
            }
        }
    }
}
