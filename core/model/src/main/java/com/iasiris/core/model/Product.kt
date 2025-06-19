package com.iasiris.core.model

data class Product(
    val name: String,
    val description: String,
    val imageUrl: String,
    val price: Double,
    val hasDrink: Boolean,
    val category: String,
    val quantity: Int = 1
)

