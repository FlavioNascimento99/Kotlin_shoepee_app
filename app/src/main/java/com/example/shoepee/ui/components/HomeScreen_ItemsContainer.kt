package com.example.shoepee.ui.components

import androidx.compose.animation.expandVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun HomeScreen_ItemsContainer (
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.7f)
        .background(Color.Red)
    ) {

    LazyColumn(modifier = Modifier) {
        items(10) { itens ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {

                // First card of Row
                Card(modifier = Modifier
                        .width(160.dp)
                        .height(80.dp)
                        .border(2.dp, Color.Black, shape = RoundedCornerShape(15.dp))
                        .align(Alignment.CenterVertically),
                    )
                {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Item", textAlign = TextAlign.Center)
                    }
                }




                // Second one
                Card(modifier = Modifier
                        .width(160.dp)
                        .height(80.dp)
                        .border(2.dp, Color.Black, shape = RoundedCornerShape(15.dp))
                        .align(Alignment.CenterVertically),
                )
                {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Item", textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}