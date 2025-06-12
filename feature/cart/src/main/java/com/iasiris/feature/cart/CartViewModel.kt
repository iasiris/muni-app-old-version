package com.iasiris.feature.cart

import androidx.lifecycle.ViewModel
import com.iasiris.core.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CartViewModel: ViewModel() {
    private val _cartUiState = MutableStateFlow(CartUiState())
    val cartUiState: StateFlow<CartUiState> = _cartUiState
}

data class CartUiState(
    val product: Product = Product("", "", "", 0.0, false, ""), //TODO check this
)