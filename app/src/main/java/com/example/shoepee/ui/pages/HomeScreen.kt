package com.example.shoepee.ui.pages
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoepee.ui.pages.components.LazyRow_Brands
import com.example.shoepee.ui.pages.components.HomeScreen_ItemsContainer


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeScreen(
    onProfileClick: () -> Unit = {}
    ) {
    var isProfileScreenVisible by remember { mutableStateOf(false) }

    if (isProfileScreenVisible) {
        ProfileScreen("user.flavio", "user.login", "user.password", null, onBackClick = {isProfileScreenVisible = false})
    } else {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    modifier = Modifier,
                    title = {
                        Text("Shoepee",
                            fontSize = 24.sp, // Aumenta o tamanho da fonte
                            color = Color.White)

                    },
                    actions = {
                        IconButton(onClick = { isProfileScreenVisible = true } ) {
                            Icon(
                                imageVector = Icons.Rounded.Person,
                                contentDescription = "text"
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
}



