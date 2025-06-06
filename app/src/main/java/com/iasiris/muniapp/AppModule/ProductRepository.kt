package com.iasiris.muniapp.AppModule

import com.iasiris.muniapp.data.model.Product
import com.iasiris.muniapp.data.model.products
import javax.inject.Inject

interface ProductRepository {
    fun getProducts(): List<Product>
}

class FakeProductRepository @Inject constructor() : ProductRepository {
    override fun getProducts(): List<Product> {
        return products
    }
}
