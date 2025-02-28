package com.example.shoepee.ui.pages.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PostCard(
    title: String,
    description: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()


    )
        {
            Column(
                modifier = Modifier.padding(50.dp)
            ) {
                Text(text = title, style = MaterialTheme.typography.titleLarge)
                Text(text = description, style = MaterialTheme.typography.bodySmall)
            }
        }
}

//@Preview(showBackground = true)
//@Composable
//fun PostCardPreview() {
//    MaterialTheme {
//        PostCard("title", "some description")
//    }
//}