package com.iasiris.core.model

data class Product(
    val name: String,
    val description: String,
    val price: String,
    val hasDrink: Boolean,
    val imageUrl: String,
    val category: String
)

