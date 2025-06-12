package com.iasiris.feature.home.productcatalog

import androidx.lifecycle.ViewModel
import com.iasiris.core.model.Product
import com.iasiris.data.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ProductCatalogViewModel : ViewModel() {
    private val productRepository = ProductRepository()
    private val _prodCatUiState = MutableStateFlow(ProductCatalogUiState())
    private val allProducts = productRepository.getProducts()
    val prodCatUiState: StateFlow<ProductCatalogUiState> = _prodCatUiState

    init {
        getProducts()
    }

    fun onCategorySelected(category: String) {
        _prodCatUiState.update { state ->
            val newCategory = if (state.selectedCategory == category) "" else category
            state.copy(
                selectedCategory = newCategory,
                products = filterProducts(state.searchText, newCategory)
            )
        }
    }

    fun onSearchTextChange(text: String) {
        _prodCatUiState.update { state ->
            state.copy(
                searchText = text,
                products = filterProducts(text, state.selectedCategory)
            )
        }
    }

    fun onOrderSelected(order: PriceOrder) {
        _prodCatUiState.update { state ->
            val sortedProducts = when (order) {
                PriceOrder.ASCENDING -> state.products.sortedBy { it.price }
                PriceOrder.DESCENDING -> state.products.sortedByDescending { it.price }
                PriceOrder.NONE -> filterProducts(state.searchText, state.selectedCategory)
            }
            state.copy(selectedOrder = order, products = sortedProducts)
        }
    }

    private fun getProducts() {
        _prodCatUiState.update { state ->
            state.copy(products = allProducts)
        }
    }

    private fun filterProducts(searchText: String, selectedCategory: String): List<Product> {
        return allProducts.filter { product ->
            val matchesSearch = product.name.contains(searchText, ignoreCase = true)
            val matchesCategory = selectedCategory.isBlank() || product.category.equals(
                selectedCategory,
                ignoreCase = true
            )
            matchesSearch && matchesCategory
        }
    }
}

data class ProductCatalogUiState(
    val searchText: String = "",
    val isSearching: Boolean = false,
    val products: List<Product> = emptyList(),
    val categories: List<String> = listOf(
        "Hamburguesas",
        "Tacos",
        "Empanadas"
    ), //TODO TAKE THIS FROM API
    val selectedCategory: String = "",
    val selectedOrder: PriceOrder = PriceOrder.NONE
)

enum class PriceOrder {
    NONE,
    ASCENDING,
    DESCENDING
}