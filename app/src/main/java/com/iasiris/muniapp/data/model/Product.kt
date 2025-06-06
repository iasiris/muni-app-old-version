package com.iasiris.muniapp.data.model

import com.iasiris.muniapp.R

data class Product(
    val name: String,
    val description: String,
    val price: String,
    val hasDrink: Boolean,
    val image: String
)

val products = listOf(
    Product(
        "Product 1",
        "Description 1",
        "10.00",
        true,
        R.drawable.muni_launcher_background.toString()
    ),
    Product(
        "Product 2",
        "Description 2",
        "20.00",
        false,
        R.drawable.muni_launcher_background.toString()
    ),
    Product(
        "Product 3",
        "Description 3",
        "30.00",
        true,
        R.drawable.muni_launcher_background.toString()
    ),
    Product(
        "Product 4",
        "Description 4",
        "40.00",
        false,
        R.drawable.muni_launcher_background.toString()
    ),
    Product(
        "Product 5",
        "Description 5",
        "50.00",
        true,
        R.drawable.muni_launcher_background.toString()
    )
)
