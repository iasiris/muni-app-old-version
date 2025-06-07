package com.iasiris.muniapp.data.model

import com.iasiris.muniapp.R

data class Product(
    val name: String,
    val description: String,
    val price: String,
    val hasDrink: Boolean,
    val imageUrl: String,
    val category: String
)

val products = listOf(
    Product(
        "Product 1",
        "Description 1",
        "10.00",
        true,
        "https://images.pexels.com/photos/1055272/pexels-photo-1055272.jpeg",
        "Cafeteria"
    ),
    Product(
        "Product 2",
        "Description 2",
        "20.00",
        false,
        "https://images.pexels.com/photos/2739250/pexels-photo-2739250.jpeg",
        "Hamburguesa"
    ),
    Product(
        "Product 3",
        "Description 3",
        "30.00",
        true,
        "https://images.pexels.com/photos/1397309/pexels-photo-1397309.jpeg",
        "Helado"
    ),
    Product(
        "Product 4",
        "Description 4",
        "40.00",
        false,
        "https://images.pexels.com/photos/29793760/pexels-photo-29793760/free-photo-of-deliciosa-pizza-de-queso-y-verduras-en-plato.jpeg",
        "Pizza"
    ),
    Product(
        "Product 5",
        "Description 5",
        "50.00",
        true,
        "https://images.pexels.com/photos/948421/pexels-photo-948421.jpeg",
        "Pizza"
    )
)
