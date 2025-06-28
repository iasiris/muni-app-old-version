package com.iasiris.data

import com.iasiris.core.model.Product

class CartRepository {

    fun addProduct(productId: String) {} //TODO

    fun removeProduct(productId: String) {} //TODO

    fun updateQuantity(quantity: Int) {} //TODO

    fun getCartItems(): List<Product> {
        // TODO fetch cart items from a data source
        return listOf(
            Product("1", "Product 1", "Description 1", 10.0, true,"image_url_1"),
            Product("2", "Product 2", "Description 2", 20.0, false,"image_url_2")
        )
    }
}