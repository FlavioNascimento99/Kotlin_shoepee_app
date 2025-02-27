package com.example.shoepee.ui.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onSignUpComplete: () -> Unit,
    onBackClick: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    var usernameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var cpfError by remember { mutableStateOf(false) }

    val context = LocalContext.current

    // Função para validar CPF (Básica, sem dígito verificador)
    fun isValidCPF(cpf: String): Boolean {
        val digits = cpf.filter { it.isDigit() }
        return digits.length == 11
    }

    // Função para validar e-mail corretamente
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Cadastro", fontSize = 32.sp, color = Color(0xFFFFA500))
        Spacer(modifier = Modifier.height(16.dp))

        // Nome do usuário
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                usernameError = it.isBlank()
            },
            label = { Text("Nome do Usuário") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Nome do Usuário") },
            isError = usernameError,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        if (usernameError) {
            Text("O nome não pode estar vazio", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = !isValidEmail(it)
            },
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
            isError = emailError,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
        )
        if (emailError) {
            Text("Digite um e-mail válido", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Senha
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = it.length < 8
            },
            label = { Text("Senha") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Senha") },
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next)
        )
        if (passwordError) {
            Text("A senha deve ter pelo menos 8 caracteres", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // CPF
        OutlinedTextField(
            value = cpf,
            onValueChange = { newCpf ->
                cpf = newCpf.filter { it.isDigit() }.take(11) // Permite apenas números
                cpfError = !isValidCPF(cpf)
            },
            label = { Text("CPF") },
            isError = cpfError,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
        )
        if (cpfError) {
            Text("Digite um CPF válido (11 dígitos)", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Registrar
        Button(
            onClick = {
                usernameError = username.isBlank()
                emailError = !isValidEmail(email)
                passwordError = password.length < 8
                cpfError = !isValidCPF(cpf)

                if (!usernameError && !emailError && !passwordError && !cpfError) {
                    Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                    onSignUpComplete()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Registrar", fontSize = 16.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botão Voltar
        TextButton(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Voltar", fontSize = 16.sp, color = Color(0xFFFFA500))
        }
    }
}


