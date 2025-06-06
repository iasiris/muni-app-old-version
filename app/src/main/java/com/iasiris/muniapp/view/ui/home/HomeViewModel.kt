package com.iasiris.muniapp.view.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val MAX_QUANTITY = 10
    private val MIN_QUANTITY = 1

    var query by mutableStateOf("")
    val categories = listOf("Todo", "Computadoras", "Celulares", "Gaming")
    var quantity by mutableIntStateOf(MIN_QUANTITY)
        private set


    fun onAdd() { //TODO check this
        Log.d("HomeViewModel", "quantity: $quantity")
        if (quantity < MAX_QUANTITY) {
            quantity++
            Log.d("HomeViewModel", "onAdd: $quantity")
        } else {
            //TODO mostrar notificacion de error
        }
    }

    fun onRemove() {
        Log.d("HomeViewModel", "quantity: $quantity")
        if (quantity > MIN_QUANTITY) {
            quantity--
            Log.d("HomeViewModel", "onRemove: $quantity")
        } else {
            //TODO mostrar notificacion de error
        }
    }

    fun onAddToCart() {
        //TODO agregar Implementación
    }
}