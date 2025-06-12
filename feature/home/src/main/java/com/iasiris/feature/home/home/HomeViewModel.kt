package com.iasiris.feature.home.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.iasiris.data.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.iasiris.core.model.Product

class HomeViewModel : ViewModel() {

    val categories = listOf("Todo", "Computadoras", "Celulares", "Gaming")
    private val productRepository = ProductRepository()
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    fun onAdd() { //TODO check this
        Log.d("HomeViewModel", "quantity: ${_homeUiState.value.quantity}")
        if (_homeUiState.value.quantity < _homeUiState.value.MAX_QUANTITY) {
            _homeUiState.update { state ->
                var quantity = _homeUiState.value.quantity
                state.copy(quantity = quantity++)
            }
            Log.d("HomeViewModel", "onAdd: $${_homeUiState.value.quantity}")
        } else {
            //TODO mostrar notificacion de error
        }
    }

    fun onRemove() {
        Log.d("HomeViewModel", "quantity: ${_homeUiState.value.quantity}")
        if (_homeUiState.value.quantity > _homeUiState.value.MIN_QUANTITY) {
            _homeUiState.update { state ->
                var quantity = _homeUiState.value.quantity
                state.copy(quantity = quantity--)
            }
            Log.d("HomeViewModel", "onRemove: ${_homeUiState.value.quantity}")
        } else {
            //TODO mostrar notificacion de error
        }
    }

    fun onAddToCart() {
        //TODO agregar Implementación
    }

    fun getProducts() {
        _homeUiState.update { state ->
            state.copy(products = productRepository.getProducts())
        }
    }
}

data class HomeUiState(
    val MAX_QUANTITY: Int = 10,
    val MIN_QUANTITY: Int = 1,
    val quantity: Int = 1,
    val query: String = "",
    val products: List<Product> = emptyList()
)