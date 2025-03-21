package com.example.shoepee.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoepee.entity.Product
import com.example.shoepee.services.ProductService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(product: Product, onBackClick: () -> Unit) {
    val productService = remember { ProductService() }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Detalhes do Produto", fontSize = 24.sp, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.Rounded.Person,
                            contentDescription = "Back"
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
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Exemplo de dados hardcoded
                Text(product.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Preço: R$ ${product.price}", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Descrição: ${product.description}", fontSize = 16.sp)

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    productService.addProduct(product, onSuccess = {
                    }, onFailure = { exception ->
                        println("Erro ao salvar produto: ${exception.message}")
                    })
                }) {
                    Text("Salvar Produto")
                }
            }
        }
    )
}


