package com.iasiris.feature.home.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iasiris.feature.home.R
import com.iasiris.feature.home.productcatalog.ProductCatalog
import com.iasiris.library.utils.paddingMedium
import com.iasiris.library.utils.sizeLarge
import com.iasiris.library.utils.ui.components.SubheadText
import com.iasiris.library.utils.ui.theme.MuniAppTheme

@Composable
fun Home(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homeViewModel: HomeViewModel = viewModel()
) {
    val homeUiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeMainRow(navigateToCart = { }) //TODO navegar a la pantalla del carrito

            Spacer(modifier = Modifier.height(paddingMedium))

            ProductCatalog(
                modifier = Modifier.padding(paddingMedium),
                navController = navController
            )

        }
    }
}

@Composable
fun HomeMainRow( //TODO convertir en topbar
    navigateToCart: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = paddingMedium)
    ) {

        SubheadText(
            text = stringResource(id = R.string.muni),
            textAlign = TextAlign.Left
        )

        IconButton(
            modifier = Modifier.size(sizeLarge),
            onClick = navigateToCart
        ) {
            Icon(
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = stringResource(id = R.string.cart_icon),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    MuniAppTheme {
        Home(
            Modifier.padding(paddingMedium),
            navController = rememberNavController()
        )
    }
}