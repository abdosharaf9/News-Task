package com.abdosharaf.newstask.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.abdosharaf.newstask.presentation.navigation.AppNavigation
import com.abdosharaf.newstask.presentation.theme.NewsTaskTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var showSplash = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        installSplashScreen().setKeepOnScreenCondition { showSplash }
        hideSplash()

        setContent {
            val navController = rememberNavController()

            NewsTaskTheme {
                AppNavigation(
                    navController = navController,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

    private fun hideSplash() {
        lifecycleScope.launch {
            delay(1000L)
            showSplash = false
        }
    }
}