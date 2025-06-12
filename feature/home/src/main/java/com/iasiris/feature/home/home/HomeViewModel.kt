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

}

data class HomeUiState(
    val MAX_QUANTITY: Int = 10,
    val MIN_QUANTITY: Int = 1,
    val quantity: Int = 1,
    val query: String = "",
    val products: List<Product> = emptyList()
)