package com.example.shoepee.ui.pages.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoepee.R

@Preview
@Composable
fun LazyRow_Brands() {
    val brandIcons = remember {
        mapOf(
            "Nike" to R.drawable.ic_nike,
            "Adidas" to R.drawable.ic_adidas,
            "New Balance" to R.drawable.ic_new_balance,
            "Mizuno" to R.drawable.ic_mizuno,
            "Puma" to R.drawable.ic_puma,
            "Gucci" to R.drawable.ic_gucci,
            "Gucci" to R.drawable.ic_gucci,
            "Balenciaga" to R.drawable.ic_balenciaga,
            "Reebok" to R.drawable.ic_reebok,
            "Converse" to R.drawable.ic_converse,
            "Timberland" to R.drawable.ic_timberland,
            "Asics" to R.drawable.ic_asics,
            "Under Armour" to R.drawable.ic_underarmour,
            "Vans" to R.drawable.ic_vans
        )
    }

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

                shape = RoundedCornerShape(50.dp)

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.5.dp)
                        .background(Color(0xFFFF9800)),

                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    val iconRes = brandIcons[brand] ?: R.drawable.ic_default
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = "Logo de $brand",
                            modifier = Modifier.size(44.dp),
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }}}