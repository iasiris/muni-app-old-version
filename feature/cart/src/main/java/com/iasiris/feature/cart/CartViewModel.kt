package com.iasiris.feature.cart

import androidx.lifecycle.ViewModel
import com.iasiris.core.model.Product
import com.iasiris.data.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CartViewModel : ViewModel() {
    //TODO agregar procesos asincronicos
    //TODO agregar CartRepository para manejar la logica de negocio
    private val productRepository = ProductRepository()
    private val allProducts = productRepository.getProducts()

    private val _cartUiState = MutableStateFlow(CartUiState())
    val cartUiState: StateFlow<CartUiState> = _cartUiState

    init {
        getProducts()
    }

    fun onAddProduct(productAdd: Product) {
        _cartUiState.update { state ->
            val updatedProducts = state.products.map {
                if (it.name == productAdd.name && it.quantity < state.MAX_PRODUCT_QUANTITY) {
                    it.copy(quantity = it.quantity + 1)
                } else it
            }
            state.copy(products = updatedProducts)
        }
        updateTotal()
    }

    fun onRemoveProduct(productRemoved: Product) {
        _cartUiState.update { state ->
            val updatedProducts = state.products.mapNotNull {
                if (it.name == productRemoved.name && it.quantity > state.MIN_PRODUCT_QUANTITY) {
                    it.copy(quantity = it.quantity - 1)
                } else it
            }
            state.copy(products = updatedProducts)
        }
        updateTotal()
    }

    fun onDeleteProduct(productDeleted: Product) {
        _cartUiState.update { state ->
            val updatedProducts = state.products.filterNot {
                it.name == productDeleted.name
            }
            state.copy(products = updatedProducts)
        }
        updateTotal()
    }

    private fun getProducts() { //TODO reemplzar con getProductsById()
        _cartUiState.update { state ->
            state.copy(products = allProducts)
        }
        updateTotal()
    }

    private fun updateTotal() {
        _cartUiState.update { state ->
            val subTotal = state.products.sumOf { it.price * it.quantity }
            val deliveryFee = Math.round(subTotal * state.FEE_PERCENTAGE * 100) / 100.0
            val totalAmount = subTotal + state.deliveryFee
            state.copy(
                subTotal = subTotal,
                deliveryFee = deliveryFee,
                totalAmount = totalAmount
            )
        }
    }
}

data class CartUiState(
    val MAX_PRODUCT_QUANTITY: Int = 10,
    val MIN_PRODUCT_QUANTITY: Int = 1,
    val FEE_PERCENTAGE: Double = 0.03,
    val products: List<Product> = emptyList(),
    val subTotal: Double = 0.0,
    val deliveryFee: Double = 0.0,
    val totalAmount: Double = 0.0
)