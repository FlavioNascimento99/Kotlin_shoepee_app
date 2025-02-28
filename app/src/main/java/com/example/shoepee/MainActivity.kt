package com.example.shoepee


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*

import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp

import com.example.shoepee.ui.pages.HomeScreen
import com.example.shoepee.ui.pages.LoginScreen
import com.example.shoepee.ui.pages.ProfileScreen
import com.example.shoepee.ui.pages.RegisterScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
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

    val onLogout: () -> Unit = {
        // Lógica de logout: redireciona para a tela de login
        isAuthenticated = false
        currentScreen = "login"
        println("Usuário deslogado")
    }

    when (currentScreen) {
        "login" -> LoginScreen(
            onSignInClick = { username, password ->
                if (username.isNotBlank() && password.isNotBlank()) {
                    isAuthenticated = true
                    currentScreen = "home"
                }
            },
            onSignUpClick = { currentScreen = "register" }  // Essa linha deve mudar para a tela de registro
        )

        "register" -> RegisterScreen(onSignUpComplete = { currentScreen = "login" }, onBackClick = { currentScreen = "login" })


        "home" -> if (isAuthenticated)
            HomeScreen(
                onProfileClick = { currentScreen = "profile" }
            ) else
                currentScreen = "login"

        "profile" -> if (isAuthenticated) {
            ProfileScreen(
                userName = "Flavio Nascimento",
                userLogin = "flavionascimento",
                userPassword = "****",
                userPhotoUrl = null,
                onBackClick = { currentScreen = "home" },
                )
        } else {
            currentScreen = "login" // Caso não esteja autenticado, vai para a tela de login
        }

    }
}
