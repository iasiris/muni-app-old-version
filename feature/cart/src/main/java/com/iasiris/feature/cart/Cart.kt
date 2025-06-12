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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iasiris.library.utils.ui.theme.MuniAppTheme

@Composable
fun Cart(
    modifier: Modifier = Modifier,
    navController: NavHostController, //TODO navegar Checkout final
    cartViewModel: CartViewModel = viewModel()
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CartPreview() {
    MuniAppTheme {
        Cart(
            navController = rememberNavController()
        )
    }
}