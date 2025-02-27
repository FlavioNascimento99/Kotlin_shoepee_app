package com.example.shoepee.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onSignInClick: (String, String) -> Unit,
    onSignUpClick: () -> Unit
) {
    var passwordError by remember { mutableStateOf(false) }

    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }

    Box(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Shoepee", fontSize = 32.sp, color = Color(0xFFFF9800))
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Nome de usuário") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.8f)
            )
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = it.text.length < 8
                },
                label = { Text("Senha") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.8f)
            )
            if (passwordError) {
                Text("A senha deve ter pelo menos 8 caracteres", color = Color.Red, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (username.text.isNotBlank() && password.text.length >= 8) {
                        onSignInClick(username.text, password.text)
                    } else {
                        errorMessage = "Preencha ambos os campos"
                    }
                },

                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
                modifier = Modifier.fillMaxWidth(0.8f),
            ) {
                Text(text = "Entrar", color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onSignUpClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "Cadastrar", color = Color.White)
            }
            if (errorMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = errorMessage, color = Color.Red)
            }
        }
    }
}
