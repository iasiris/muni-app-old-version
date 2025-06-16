package com.iasiris.feature.cart

import androidx.lifecycle.ViewModel
import com.iasiris.core.model.Product
import com.iasiris.data.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CartViewModel: ViewModel() {
    private val productRepository = ProductRepository()
    private val allProducts = productRepository.getProducts()

    private val _cartUiState = MutableStateFlow(CartUiState())
    val cartUiState: StateFlow<CartUiState> = _cartUiState

    init {
        getProducts()
    }

    private fun getProducts() {
        _cartUiState.update { state ->
            state.copy(products = allProducts)
        }
    }
}

data class CartUiState(
    val products: List<Product> = emptyList(),
    val totalAmount: Double = 0.0 // TODO reemplazar por total calculado
)