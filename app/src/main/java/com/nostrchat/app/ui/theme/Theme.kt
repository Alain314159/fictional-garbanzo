package com.nostrchat.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val NostrChatTheme: @Composable () -> Unit = {
    val darkTheme = isSystemInDarkTheme()
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }
    
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = androidx.compose.material3.Shapes()
    )
}
