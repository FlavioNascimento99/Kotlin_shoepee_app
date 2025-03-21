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
import com.google.firebase.auth.FirebaseAuth


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
    var isProfileScreenVisible by remember { mutableStateOf(false) }

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
            onSignUpClick = { currentScreen = "register" }
        )



        "register" -> RegisterScreen(onSignUpComplete = { currentScreen = "login" }, onBackClick = { currentScreen = "login" })



        "home" -> if (isAuthenticated)
            HomeScreen(
                onProfileClick = { currentScreen = "profile" }
            ) else
                currentScreen = "login"


        "profile" -> if (isAuthenticated) {
            val currentUser = FirebaseAuth.getInstance().currentUser
            ProfileScreen(
                userName = currentUser?.displayName ?: "Nome não disponível",
                userLogin = currentUser?.email ?: "E-mail não disponível",
                userPhotoUrl = currentUser?.photoUrl?.toString(),
                onBackClick = { isProfileScreenVisible = false },

                onLogoutClick = {
                    isAuthenticated = false  // Atualiza estado global
                    currentScreen = "login"  // 🔥 Redireciona IMEDIATAMENTE para login
                }
            )
        } else {
            currentScreen = "login"
        }
    }
}
