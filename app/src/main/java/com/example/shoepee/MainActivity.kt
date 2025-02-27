package com.example.shoepee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.example.shoepee.ui.screens.LoginScreen
import com.example.shoepee.ui.screens.RegisterScreen
import com.example.shoepee.ui.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf("login") }
    var isAuthenticated by remember { mutableStateOf(false) }

    when (currentScreen) {
        "login" -> LoginScreen(
            onSignInClick = { username, password ->
                if (username.isNotBlank() && password.isNotBlank()) {
                    isAuthenticated = true
                    currentScreen = "home"
                }
            },
            onSignUpClick = { currentScreen = "register" }
        )
        "register" -> RegisterScreen(
            onSignUpComplete = { currentScreen = "login" },
            onBackClick = { currentScreen = "login" }
        )
        "home" -> if (isAuthenticated) HomeScreen() else currentScreen = "login"
    }
}
