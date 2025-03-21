package com.example.shoepee.ui.pages.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoepee.services.ProductService
import com.example.shoepee.entity.Product


@Composable
fun HomeScreen_ItemsContainer(onItemClick: (Product) -> Unit) {
    val products = remember { mutableStateOf<List<Product>>(emptyList()) }
    val productService = remember { ProductService() }
    val hardcodedProducts = listOf(
        Product(
            "Nike Air Max 2021",
            "Tênis de corrida com muito estilo e conforto.",
            "https://exemplo.com/nike_air_max_2021.jpg",
            499.99,
            "Nike"
        ),
        Product(
            "Adidas Ultraboost",
            "Tênis de corrida com amortecimento superior.",
            "https://exemplo.com/adidas_ultraboost.jpg",

            599.99,
            "Adidas"
        ),
        Product(
            "Puma RS-X",
            "Tênis esportivo com design ousado.",
            "https://exemplo.com/puma_rsx.jpg",
            399.99,
            "Puma"
        ),
        Product(
            "Nike React Element",
            "Tênis com excelente estabilidade e conforto.",
            "https://exemplo.com/nike_react_element.jpg",
            449.99,
            "Nike"
        ),
        Product(
            "New Balance 990v5",
            "Tênis de corrida com excelente suporte.",
            "https://exemplo.com/new_balance_990v5.jpg",
            550.00,
            "New Balance"
        ),
        Product(
            "Reebok Classic Leather",
            "Tênis clássico e confortável para o dia a dia.",
            "https://exemplo.com/reebok_classic_leather.jpg",
            379.99,
            "Reebok"
        ),
        Product(
            "Nike Air Zoom Pegasus 37",
            "Tênis de corrida com conforto e estabilidade.",
            "https://exemplo.com/nike_air_zoom_pegasus_37.jpg",
            549.99,
            "Nike"
        ),
        Product(
            "Asics Gel Kayano 27",
            "Tênis de corrida com excelente amortecimento.",
            "https://exemplo.com/asics_gel_kayano_27.jpg",
            629.99,
            "Asics"
        ),
        Product(
            "Saucony Triumph 18",
            "Tênis para corrida com conforto premium.",
            "https://exemplo.com/saucony_triumph_18.jpg",
            620.00,
            "Saucony"
        ),
        Product(
            "Under Armour HOVR Phantom",
            "Tênis de corrida com a tecnologia HOVR para maior retorno de energia.",
            "https://exemplo.com/under_armour_hovr_phantom.jpg",
            699.99,
            "Under Armour"
        ),
        Product(
            "Vans Old Skool",
            "Tênis casual clássico, ideal para o dia a dia.",
            "https://exemplo.com/vans_old_skool.jpg",
            299.99,
            "Vans"
        ),
        Product(
            "Converse Chuck Taylor All Star",
            "Tênis clássico e confortável, com design atemporal.",
            "https://exemplo.com/converse_chuck_taylor.jpg",
            249.99,
            "Converse"
        )
    )

//    LaunchedEffect(Unit) {
//        productService.fetchProducts { productList ->
//            products.value = productList
//        }
//    }

    LaunchedEffect(Unit) {
        products.value = hardcodedProducts
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(products.value) { index, product ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ProductCard(product)
                Spacer(modifier = Modifier.width(16.dp))
                if (index < products.value.size - 1) {
                    ProductCard(products.value[index + 1])
                }
            }
        }
    }
}

    @Composable
fun ProductCard(product: Product) {

    Card(
        modifier = Modifier
            .width(200.dp)
            .height(300.dp)
            .padding(10.dp)
            .border(2.dp, Color.Black, shape = RoundedCornerShape(15.dp)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = product.description,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
//                Text(product.imageUrl)
                Text("Imagem URL")

            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${product.price}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "10x de R$ ${String.format("%.2f", product.price / 10)}",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {



            }) {
                Text("Mais Detalhes")
            }
        }


    }
}
