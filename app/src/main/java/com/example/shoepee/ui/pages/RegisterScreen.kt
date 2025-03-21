package com.example.shoepee.ui.pages

import android.annotation.SuppressLint
import android.net.http.HttpException
import android.util.Log
import com.example.shoepee.entity.Address

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoepee.services.AddressApi
import com.example.shoepee.services.AddressService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onSignUpComplete: () -> Unit,
    onBackClick: () -> Unit
) {
    // Parâmetros da Entidade
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf(Address()) }
    var cep by remember { mutableStateOf("") }

    // Excessões
    var errorMessage by remember { mutableStateOf("") }
    var usernameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var ageError by remember { mutableStateOf(false) }
    var cpfError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }
    var addressError by remember { mutableStateOf(false) }
    var cepError by remember { mutableStateOf(false) }


    val context = LocalContext.current

    // Função para validar e-mail corretamente
    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Instância do FirebaseAuth e FirebaseFirestore
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()


    // Função para buscar o endereço usando o CEP (via ViaCep)
    fun getAddressFromCep(cep: String) {
        val cleanCep = cep.replace("-", "") // Remove o traço do CEP

        if (cleanCep.length == 8) {
            // Use a função de corrotina dentro de um escopo de corrotina
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val addressData = AddressApi.service.getAddress(cleanCep)

                    // Log para verificar a resposta
                    Log.d("AddressResponse", "Endereco recebido: $addressData")

                    // Mapeamento correto para o seu objeto Address
                    val mappedAddress = Address(
                        cep = addressData.cep,
                        street = addressData.street,
                        neighborhood = addressData.neighborhood,
                        city = addressData.city,
                        state = addressData.state
                    )



                    // Atualiza o estado do endereço na thread principal
                    withContext(Dispatchers.Main) {
                        address = mappedAddress
                        errorMessage = "" // Reseta a mensagem de erro
                    }
                } catch (@SuppressLint("NewApi") e: HttpException) {
                    // Atualiza o estado do erro na thread principal
                    withContext(Dispatchers.Main) {
                        errorMessage = "Erro ao buscar endereço. Tente novamente."
                    }
                } catch (e: Exception) {
                    // Atualiza o estado do erro na thread principal
                    withContext(Dispatchers.Main) {
                        errorMessage = "Erro desconhecido. Tente novamente."
                    }
                }
            }
        }
    }




    // Função para registrar o usuário no Firebase
    fun registerUser(email: String, password: String, username: String, age: String, cpf: String, phone: String, address: Address) {

        // Método de criação de conta do Firebase (Email/Conta)
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    
                    /*
                        Agora, salvamos os dados do usuário no Documento do Firestore.
                        
                        OBS: Nesse caso, usuário e email são transmitidos por aqui enquanto que a senha é tratada pelo Firebase internamente.
                    */
                    user?.let {
                        val userData = hashMapOf(
                            "username" to username,
                            "email" to email,
                            "age" to age,
                            "cpf" to cpf,
                            "phone" to phone,
                            "address" to hashMapOf(
                                "cep" to address.cep,
                                "street" to address.street,
                                "neighborhood" to address.neighborhood,
                                "city" to address.city,
                                "state" to address.state,
                            ),
                        )

                        // Criando o documento do usuário no Firestore
                        firestore.collection("users")
                            .document(user.uid)     // UID do usuário autenticado
                            .set(userData)          // Mapeamento de campos do Documento Firebase
                            .addOnSuccessListener {

                                // Sucesso ao salvar os dados no Firestore
                                Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()

                                // Redirecionador
                                onSignUpComplete()
                            
                            }
                            .addOnFailureListener { exception ->
                                // Se for apresentado falha ao salvar os dados durante a conexão com Banco.
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
        /**
         *
         * Cadastro de Usuário com os campos:
         *
         */
        Text(text = "Cadastro", fontSize = 32.sp, color = Color(0xFFFFA500))
        Spacer(modifier = Modifier.height(16.dp))




        /**
         *
         *  Nome do Usuário
         *
         */
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




        /**
         *
         * Idade do Usuário
         *
         */
        OutlinedTextField(
            value = age,
            onValueChange = {
                age = it
                ageError = it.isBlank()
            },
            label = { Text("Idade") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Idade do Usuário") },
            isError = ageError,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        if (ageError) {
            Text("Por favor, insira uma idade válida", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))





        /**
         *
         * CEP
         *
         */
        OutlinedTextField(
            value = cep,
            onValueChange = {
                cep = it
                cepError = it.length != 8
                if (it.length == 8) {
                    getAddressFromCep(it) // Buscar o endereço ao preencher o CEP
                }
            },
            label = { Text("CEP") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "CEP") },
            isError = cepError,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
        )
        if (cepError) {
            Text("CEP inválido", color = Color.Red, fontSize = 12.sp)
        }

        // Exibe a mensagem de erro do CEP
        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = Color.Red, fontSize = 12.sp)
        }




        /**
         *
         * Telefone do Usuário
         *
         */
        OutlinedTextField(
            value = phone,
            onValueChange = {
                phone = it
                phoneError = it.isBlank()
            },
            label = { Text("Telefone") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Telefone") },
            isError = phoneError,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        if (phoneError) {
            Text("Informe um número de telefone válido", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))




        /**
         *
         * CPF do Usuário
         *
         */
        OutlinedTextField(
            value = cpf,
            onValueChange = {
                cpf = it
                cpfError = it.isBlank()
            },
            label = { Text("CPF") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "CPF") },
            isError = cpfError,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        if (cpfError) {
            Text("Informe um CPF válido", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))






        /**
         *
         * Email do Usuário
         *
         */
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








        /**
         *
         * Senha do Usuário
         *
         */
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






        /**
         *
         * Botão de confirmar registro do usuário.
         *
         */
        Button(
            onClick = {
                if (username.isNotBlank() && isValidEmail(email) && password.length >= 8) {
                    registerUser(email, password, username, age, cpf, phone, address)
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


        /**
         *
         * Botão de Retorno (A tela de login)
         *
         */
        TextButton(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text(text = "Voltar", fontSize = 16.sp, color = Color(0xFFFFA500))
        }



    }
}
