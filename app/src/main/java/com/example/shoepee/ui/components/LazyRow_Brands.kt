package com.example.shoepee.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun LazyRow_Brands() {
    val brandCollection = remember {
        mutableStateOf(
            listOf(
                "Nike",
                "Adidas",
                "New Balance",
                "Mizuno",
                "Balenciaga",
                "Gucci",
                "Puma",
                "Reebok",
                "Converse",
                "Timberland",
                "Asics",
                "Under Armour",
                "Vans"
            )
        )
    }

    LazyRow(
        modifier = Modifier
            .padding(16.dp)
    ) {
        items(brandCollection.value) {
            brand ->

            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .width(75.dp)
                    .height(75.dp),
                shape = RoundedCornerShape(50.dp),
            ) {
                Text(
                    text = brand,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}