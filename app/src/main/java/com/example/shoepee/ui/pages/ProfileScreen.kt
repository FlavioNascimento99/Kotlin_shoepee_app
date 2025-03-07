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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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


// Tela de visualização de usuário (quando logado)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    userName: String,
    userLogin: String,
    userPassword: String,
    userPhotoUrl: String? = null,
    onBackClick: () -> Unit = {}
) {


    // Criando instância de conexão com Banco de Dados Firebase
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser


    // Presets para dados caso os mesmos sejam "não-nulos"
    if (currentUser != null) {
        val userName = currentUser.displayName ?: "Nome não disponível"
        val userLogin = currentUser.email ?: "E-mail não disponível"
        val userPassword = "********"
        val userPhotoUrl = currentUser.photoUrl?.toString()

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text("Profile", fontSize = 24.sp, color = Color.White)
                    },
                    navigationIcon = {

                        // Função para navegação onBackClick() muda propriedade 
                        IconButton(onClick = { onBackClick() }) {
                            Icon(
                                imageVector = Icons.Default.Home,
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
                    if (userPhotoUrl != null) {
                        Image(
                            painter = rememberAsyncImagePainter(userPhotoUrl),
                            contentDescription = "User Profile Image",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = userName,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Login: $userLogin",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )

                    Text(
                        text = "(Experimental)Password: $userPassword",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            
                            // @TODO: Adicionar evento de logout
                        
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Logout")
                    }
                }
            }
        )
    }
}