package com.example.shoepee.ui.pages

import android.util.Patterns
import android.widget.Toast
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

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
    var errorMessage by remember { mutableStateOf("") }

    var usernameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    val context = LocalContext.current

    // Função para validar e-mail corretamente
    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Instância do FirebaseAuth e FirebaseFirestore
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()




    // Função para registrar o usuário no Firebase
    fun registerUser(email: String, password: String, username: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    // Agora, salvamos os dados do usuário no Firestore
                    user?.let {
                        val userData = hashMapOf(
                            "username" to username,
                            "email" to email
                        )

                        // Criando o documento do usuário no Firestore
                        firestore.collection("users")
                            .document(user.uid)  // UID do usuário autenticado
                            .set(userData)
                            .addOnSuccessListener {
                                // Sucesso ao salvar os dados no Firestore
                                Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                                onSignUpComplete()
                            }
                            .addOnFailureListener { exception ->
                                // Falha ao salvar os dados
                                Toast.makeText(context, "Erro ao salvar dados: ${exception.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                } else {
                    // Falha no registro do usuário
                    Toast.makeText(context, "Erro no cadastro: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
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

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Registrar
        Button(
            onClick = {
                if (username.isNotBlank() && isValidEmail(email) && password.length >= 8) {
                    registerUser(email, password, username)
                } else {
                    Toast.makeText(context, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
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
