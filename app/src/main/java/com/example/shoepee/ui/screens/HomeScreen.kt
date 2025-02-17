package com.example.shoepee.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoepee.ui.components.HomeScreen_ItemsContainer
import com.example.shoepee.ui.components.LazyRow_Brands

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .border(5.dp, Color.White, shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)),
                title = {
                    Text("Hello")
                },
                actions = {
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = "text"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.DarkGray
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize() // Garante que a Column ocupa todo o espaço disponível
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp) // Dá um espaçamento mais natural
            ) {
                LazyRow_Brands()

                HomeScreen_ItemsContainer(
                    modifier = Modifier.border(10.dp, Color.Black)
                )
            }
        }
    )
}



