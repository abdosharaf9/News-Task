package com.abdosharaf.newstask.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun NewsTaskTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current

    SideEffect {
        val statusBarColor = colorScheme.background
        val navigationBarColor = colorScheme.background
        val activity = view.context as Activity
        val window = activity.window

        window.statusBarColor = statusBarColor.toArgb()
        window.navigationBarColor = navigationBarColor.toArgb()

        val windowInsetsController = WindowInsetsControllerCompat(window, view)
        windowInsetsController.isAppearanceLightStatusBars = true
        windowInsetsController.isAppearanceLightNavigationBars = true
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}