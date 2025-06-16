package com.iasiris.feature.home.productdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iasiris.core.model.Product
import com.iasiris.data.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ProductDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val productId: String = checkNotNull(savedStateHandle["id"])
    private val productRepository = ProductRepository()
    private val product = productRepository.getProductByName(productId)

    private val _prodDetailUiState =
        MutableStateFlow(ProductDetailUiState(product = product, totalAmount = product.price))
    val prodDetailUiState: StateFlow<ProductDetailUiState> = _prodDetailUiState

    fun onAdd() {
        val currentQuantity = _prodDetailUiState.value.quantity
        if (currentQuantity < _prodDetailUiState.value.MAX_QUANTITY) {
            _prodDetailUiState.update { state ->
                val newQuantity = currentQuantity + 1
                state.copy(
                    quantity = newQuantity,
                    totalAmount = newQuantity * state.product.price
                )

            }
        } else {
            //TODO mostrar notificacion de error
        }
    }

    fun onRemove() {
        val currentQuantity = _prodDetailUiState.value.quantity
        if (currentQuantity > _prodDetailUiState.value.MIN_QUANTITY) {
            _prodDetailUiState.update { state ->
                val newQuantity = currentQuantity - 1
                state.copy(
                    quantity = newQuantity,
                    totalAmount = newQuantity * state.product.price
                )
            }
        } else {
            //TODO mostrar notificacion de error
        }
    }

    fun onAddToCart() {
        //TODO agregar Implementación
    }
}

data class ProductDetailUiState(
    val product: Product,
    val MAX_QUANTITY: Int = 10,
    val MIN_QUANTITY: Int = 1,
    val quantity: Int = 1,
    val totalAmount: Double
)