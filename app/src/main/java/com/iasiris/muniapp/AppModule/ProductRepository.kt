package com.iasiris.muniapp.AppModule

import com.iasiris.muniapp.data.model.Producto
import javax.inject.Inject

interface ProductRepository {
    fun getProductos(): List<Producto>
}

class FakeProductRepository @Inject constructor() : ProductRepository {
    override fun getProductos(): List<Producto> {
        // Devuelve una lista estática de productos
        return listOf(
            Producto("Milanesa", "Loren ipsum", "123", true),
            Producto("Hamburguesa", "Loren ipsum", "512", false),
            Producto("Taco", "Loren ipsum", "253", false),
            Producto("Empanada", "Loren ipsum", "963", true)
        )
    }
}
