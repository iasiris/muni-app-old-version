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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iasiris.muniapp.R
import com.iasiris.muniapp.data.model.Producto
import com.iasiris.muniapp.ui.theme.MuniAppTheme
import com.iasiris.muniapp.utils.components.CaptionText
import com.iasiris.muniapp.utils.components.CustomCard
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
    //TODO mover a viewModel
    var query by remember { mutableStateOf("") }
    val categorias = listOf("Todo", "Computadoras", "Celulares", "Gaming")
    val productos = listOf(
        Producto("Milanesa", "Loren ipsum", "123", true),
        Producto("Hamburguesa", "Loren ipsum", "512", false),
        Producto("Taco", "Loren ipsum", "253", false),
        Producto("Empanada", "Loren ipsum", "963", true)
    )

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

            HomeMainRow(
                onClickIcon = { /*TODO*/ }
            )

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
            CustomSearchBar(searchText = query, onSearchTextChange = {})
            //Categorias
            TituloConBotonVerTodo(title = stringResource(id = R.string.categorias))

            LazyRow {
                items(categorias) { it ->
                    PillCard(it)
                }
            }

            Spacer(modifier = Modifier.height(paddingMedium))

            TituloConBotonVerTodo(title = stringResource(id = R.string.ofertas_del_dia))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {
                items(productos) { producto ->
                    CustomCard(
                        titulo = producto.nombre,
                        precio = producto.precio,
                        descripcion = producto.descripcion,
                        incluyeBebida = producto.incluyeBebida
                    )
                }
            }
        }
    }
}

@Composable
fun TituloConBotonVerTodo(title: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        SubheadText(text = title)
        TextButton(onClick = { /*TODO*/ }) {
            CaptionText(
                text = stringResource(id = R.string.ver_todo),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun HomeMainRow(
    onClickIcon: () -> Unit
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
            onClick = { onClickIcon }
        ) {
            Icon(
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = stringResource(id = R.string.carrito_icon),
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