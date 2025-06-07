package com.iasiris.muniapp.view.ui.home

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iasiris.muniapp.R
import com.iasiris.muniapp.data.model.products
import com.iasiris.muniapp.ui.theme.MuniAppTheme
import com.iasiris.muniapp.utils.components.CaptionText
import com.iasiris.muniapp.utils.components.CardWithImageInTheLeft
import com.iasiris.muniapp.utils.components.CustomSearchBar
import com.iasiris.muniapp.utils.components.PillCard
import com.iasiris.muniapp.utils.components.RowConIconoYLocacion
import com.iasiris.muniapp.utils.components.SubheadText
import com.iasiris.muniapp.utils.paddingMedium
import com.iasiris.muniapp.utils.sizeLarge

@Composable
fun Home(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel()
) {
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
                RowConIconoYLocacion(
                    location = "Lima, Peru",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            //Busqueda
            CustomSearchBar(searchText = viewModel.query, onSearchTextChange = {})
            //Categorias
            TitleWithSeeAllButton(title = stringResource(id = R.string.categorias))

            LazyRow { items(viewModel.categories) { it -> PillCard(it) } }

            Spacer(modifier = Modifier.height(paddingMedium))

            TitleWithSeeAllButton(title = stringResource(id = R.string.specials_of_the_day))

            LazyColumn {
                modifier.fillMaxSize() //TODO chequear si esto sirve para el LazyColumn
                items(products) { product ->
                    CardWithImageInTheLeft(
                        product = product,
                        quantity = viewModel.quantity,
                        onAdd = { viewModel.onAdd() },
                        onRemove = { viewModel.onRemove() },
                        onAddToCart = { viewModel.onAddToCart() }
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