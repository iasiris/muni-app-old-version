package com.iasiris.feature.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iasiris.library.utils.ui.theme.MuniAppTheme

@Composable
fun Cart(
    navigateToCheckout: () -> Unit = {}, //TODO navegar Checkout final
    cartViewModel: CartViewModel = viewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            //lazy column con productos del carrito
            //subtotal
            //delivery fee
            //button con total que lleva a detalle de compra

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartPreview() {
    MuniAppTheme {
        Cart(
            navigateToCheckout = {}
        )
    }
}