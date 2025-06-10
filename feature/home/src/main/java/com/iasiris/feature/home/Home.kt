package com.iasiris.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
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
import com.iasiris.library.utils.paddingMedium
import com.iasiris.library.utils.sizeLarge
import com.iasiris.library.utils.ui.components.CaptionText
import com.iasiris.library.utils.ui.components.CustomSearchBar
import com.iasiris.library.utils.ui.components.PillCard
import com.iasiris.library.utils.ui.components.RowWithIconAndLocation
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
            //Locacion
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                RowWithIconAndLocation(
                    location = "Lima, Peru",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            //Busqueda
            CustomSearchBar(
                searchText = homeUiState.query,
                onSearchTextChange = {})
            //Categorias
            TitleWithSeeAllButton(title = stringResource(id = R.string.categorias))

            LazyRow {
                items(homeViewModel.categories) { it ->
                    PillCard(
                        it
                    )
                }
            }

            Spacer(modifier = Modifier.height(paddingMedium))

            TitleWithSeeAllButton(title = stringResource(id = R.string.specials_of_the_day))

            LazyColumn {
                modifier.fillMaxSize() //TODO chequear si esto sirve para el LazyColumn
                items(homeUiState.products) { product ->
                    CardWithImageInTheLeft(
                        product = product,
                        quantity = homeUiState.quantity,
                        onAdd = { homeViewModel.onAdd() },
                        onRemove = { homeViewModel.onRemove() },
                        onAddToCart = { homeViewModel.onAddToCart() }
                    )
                }
            }
        }
    }
}

@Composable
fun TitleWithSeeAllButton(title: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        SubheadText(text = title)
        TextButton(onClick = { /*TODO*/ }) {
            CaptionText(
                text = stringResource(id = R.string.see_all),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun HomeMainRow(
    navigateToCart: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
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