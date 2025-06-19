package com.iasiris.muniapp.navigation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object ProductCatalog

@Serializable
data class ProductDetail(val id: String)

@Serializable
object Cart // data class Cart(val products: List<String>)

@Serializable
object Profile