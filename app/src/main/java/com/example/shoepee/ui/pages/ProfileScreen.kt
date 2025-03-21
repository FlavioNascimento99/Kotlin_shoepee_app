package com.example.shoepee.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import com.example.shoepee.entity.User


// Função de logout
fun logout(onLogout: () -> Unit) {
    val auth = FirebaseAuth.getInstance()
    auth.signOut()  // Desconecta o usuário
    onLogout() // Atualiza o estado imediatamente após logout
}

// Tela de visualização de usuário (quando logado)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    userName: String,
    userLogin: String,
    userPhotoUrl: String? = null,
    onBackClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {

    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val user by remember { mutableStateOf<User?>(null) }
    val currentUser = auth.currentUser

    // 🔥 Observa mudanças no Firebase Auth
    LaunchedEffect(currentUser) {
        if (currentUser == null) {
            onLogoutClick()  // Redireciona para login ao detectar logout
        }
    }


    if (currentUser != null) {
        val displayName = currentUser.displayName ?: "Usuário"
        val email = currentUser.email ?: "E-mail não disponível"
        val photoUrl = currentUser.photoUrl?.toString()

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text("Perfil", fontSize = 24.sp, color = Color.White)
                    },
                    navigationIcon = {
                        IconButton(onClick = { onBackClick() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFFF9800)
                    )
                )
            },

            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (photoUrl != null) {
                        Image(
                            painter = rememberAsyncImagePainter(photoUrl),
                            contentDescription = "Imagem de perfil do usuário",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = displayName,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Login: $email",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )

                    user?.let {
                        Text(text = "Nome: ${it.name}")
                        Text(text = "Email: ${it.email}")
                        Text(text = "Idade: ${it.age}")
                        Text(text = "Endereço: ${it.address}")
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            logout { onLogoutClick() }  // 🔥 Logout e navegação garantida

                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Sair")
                    }
                }
            }
        )
    }
}

// Modelo de dados do usuário
data class UserData(
    val name: String = "",
    val email: String = "",
    val age: Int = 0,
    val address: String = ""
)

