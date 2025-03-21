package com.example.shoepee.services

import com.example.shoepee.entity.Product
import com.google.firebase.firestore.FirebaseFirestore


class ProductService {

    fun addHardcodedProducts() {
        val products = listOf(
            Product(
                "OK",
                "Nike Air Max 2021",
                "Tênis de corrida com muito estilo e conforto.",
                499.99,
                "https://exemplo.com/nike_air_max_2021.jpg",
                "Nike"
            ),
            Product(
                "OK",
                "Adidas Ultraboost",
                "Tênis de corrida com amortecimento superior.",
                599.99,
                "https://exemplo.com/adidas_ultraboost.jpg",
                "Adidas"
            ),
            Product(
                "OK",
                "Puma RS-X",
                "Tênis esportivo com design ousado.",
                399.99,
                "https://exemplo.com/puma_rsx.jpg",
                "Puma"
            ),
            Product(
                "OK",
                "Nike React Element",
                "Tênis com excelente estabilidade e conforto.",
                449.99,
                "https://exemplo.com/nike_react_element.jpg",
                "Nike"
            ),
            Product(
                "OK",
                "New Balance 990v5",
                "Tênis de corrida com excelente suporte.",
                550.00,
                "https://exemplo.com/new_balance_990v5.jpg",
                "New Balance"
            ),
            Product(
                "OK",
                "Reebok Classic Leather",
                "Tênis clássico e confortável para o dia a dia.",
                379.99,
                "https://exemplo.com/reebok_classic_leather.jpg",
                "Reebok"
            ),
            Product(

                "OK",
                "Nike Air Zoom Pegasus 37",
                "Tênis de corrida com conforto e estabilidade.",
                549.99,
                "https://exemplo.com/nike_air_zoom_pegasus_37.jpg",
                "Nike"
            ),
            Product(
                "OK",

                "Asics Gel Kayano 27",
                "Tênis de corrida com excelente amortecimento.",
                629.99,
                "https://exemplo.com/asics_gel_kayano_27.jpg",
                "Asics"
            ),
            Product(
                "OK",
                "Saucony Triumph 18",
                "Tênis para corrida com conforto premium.",
                620.00,
                "https://exemplo.com/saucony_triumph_18.jpg",
                "Saucony"
            ),
            Product(
                "OK",

                "Under Armour HOVR Phantom",
                "Tênis de corrida com a tecnologia HOVR para maior retorno de energia.",
                699.99,
                "https://exemplo.com/under_armour_hovr_phantom.jpg",
                "Under Armour"
            ),
            Product(
                "OK",

                "Vans Old Skool",
                "Tênis casual clássico, ideal para o dia a dia.",
                299.99,
                "https://exemplo.com/vans_old_skool.jpg",
                "Vans"
            ),
            Product(
                "OK",
                "Converse Chuck Taylor All Star",
                "Tênis clássico e confortável, com design atemporal.",
                249.99,
                "https://exemplo.com/converse_chuck_taylor.jpg",
                "Converse"
            )
        )

        products.forEach { product ->
            println(product.toString())  // Exibe a string formatada
        }

        val db = FirebaseFirestore.getInstance()
        val productRef = db.collection("products")

        // Adiciona cada produto hardcoded ao banco de dados
        for (product in products) {
            productRef.add(product)
                .addOnSuccessListener {
                    println("Produto adicionado com sucesso!")
                }
                .addOnFailureListener { exception ->
                    println("Erro ao adicionar produto: $exception")
                }
        }
    }


    // Função para buscar produtos
    fun fetchProducts(onProductsFetched: (List<Product>) -> Unit) {
        val database = FirebaseFirestore.getInstance()

        database.collection("products")
            .get()
            .addOnSuccessListener { result ->
                val products = result.documents.mapNotNull { doc ->
                    doc.toObject(Product::class.java)?.copy(id = doc.id)
                }
                onProductsFetched(products)
            }
            .addOnFailureListener { exception ->
                onProductsFetched(emptyList())
            }
    }



    fun addProduct(product: Product, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {

        val db = FirebaseFirestore.getInstance()
        val productRef = db.collection("products").document() // Cria um novo documento

        productRef.set(product)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }
}