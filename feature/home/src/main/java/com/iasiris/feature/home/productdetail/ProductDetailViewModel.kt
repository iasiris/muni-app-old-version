package com.iasiris.feature.home.productdetail

class ProductDetailViewModel {
    /*
    fun onAdd() { //TODO check this
        Log.d("HomeViewModel", "quantity: ${_prodCatUiState.value.quantity}")
        if (_prodCatUiState.value.quantity < _prodCatUiState.value.MAX_QUANTITY) {
            _prodCatUiState.update { state ->
                var quantity = _prodCatUiState.value.quantity
                state.copy(quantity = quantity++)
            }
            Log.d("HomeViewModel", "onAdd: $${_prodCatUiState.value.quantity}")
        } else {
            //TODO mostrar notificacion de error
        }
    }

    fun onRemove() {
        Log.d("HomeViewModel", "quantity: ${_prodCatUiState.value.quantity}")
        if (_prodCatUiState.value.quantity > _prodCatUiState.value.MIN_QUANTITY) {
            _prodCatUiState.update { state ->
                var quantity = _prodCatUiState.value.quantity
                state.copy(quantity = quantity--)
            }
            Log.d("HomeViewModel", "onRemove: ${_prodCatUiState.value.quantity}")
        } else {
            //TODO mostrar notificacion de error
        }
    }

    fun onAddToCart() {
        //TODO agregar Implementación
    }*/
}

data class ProductDetailUiState(
    val MAX_QUANTITY: Int = 10,
    val MIN_QUANTITY: Int = 1,
    val quantity: Int = 1,
)