package com.example.shoepee.entity

data class Product(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val imageUrl: String = "",
    val brand: String = "",
    ) {
    override fun toString(): String {
        return "Product(name='$name', price=$price, description='$description', imageUrl='$imageUrl', brand='$brand')"
    }
}
