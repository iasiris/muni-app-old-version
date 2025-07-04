package com.iasiris.feature.home.productcatalog

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Reorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.iasiris.core.model.Product
import com.iasiris.feature.home.R
import com.iasiris.library.utils.paddingExtraSmall
import com.iasiris.library.utils.paddingMedium
import com.iasiris.library.utils.paddingSmall
import com.iasiris.library.utils.sizeLarge
import com.iasiris.library.utils.sizeMedium
import com.iasiris.library.utils.ui.components.BodyText
import com.iasiris.library.utils.ui.components.CaptionText
import com.iasiris.library.utils.ui.components.CustomSearchBar
import com.iasiris.library.utils.ui.components.PillCard
import com.iasiris.library.utils.ui.components.RowWithPriceAndHasDrink
import com.iasiris.library.utils.ui.components.SubheadText
import com.iasiris.library.utils.ui.theme.MuniAppTheme
import com.iasiris.library.utils.ui.theme.Shapes

@Composable
fun ProductCatalog(
    navigateToProductDetail: (String) -> Unit = {},
    prodCatViewModel: ProductCatalogViewModel = viewModel()
) {
    val prodCatUiState by prodCatViewModel.prodCatUiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize()
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

            CustomSearchBar(
                searchText = prodCatUiState.searchText,
                onSearchTextChange = { prodCatViewModel.onSearchTextChange(it) }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = paddingMedium)
            ) {
                LazyRow {
                    item {
                        PillCardWithDropDownMenu(
                            selectedOrder = prodCatUiState.selectedOrder,
                            onOrderSelected = { option ->
                                prodCatViewModel.onOrderSelected(option)
                            }
                        )
                    }
                    items(prodCatUiState.categories) { category ->
                        val isSelected = category == prodCatUiState.selectedCategory
                        PillCard(
                            text = category,
                            isSelected = isSelected,
                            onClick = { prodCatViewModel.onCategorySelected(category) }
                        )
                    }
                }
            }

            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = paddingMedium)
            ){
                items(prodCatUiState.products) { product ->
                    CardWithImageInTheLeft(
                        product = product,
                        navigateToProductDetail = navigateToProductDetail
                    )
                }
            }
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
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold
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

@Composable
fun CardWithImageInTheLeft(
    product: Product,
    navigateToProductDetail: (String) -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(paddingExtraSmall)
            .height(120.dp)
            .fillMaxWidth()
            .clickable {
                navigateToProductDetail(product.name)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            //TODO agregar imagen por default mientras cargan las imagenes reales
            AsyncImage(
                model = product.imageUrl,
                contentDescription = stringResource(id = R.string.product_image),
                onError = {
                    Log.i("AsyncImage", "Error loading image ${it.result.throwable.message}")
                },
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
            )

            Spacer(modifier = Modifier.width(paddingSmall))

            Column(
                modifier = Modifier.padding(end = paddingMedium)
            ) {
                BodyText(text = product.name)

                Spacer(modifier = Modifier.height(paddingExtraSmall))

                CaptionText(
                    text = product.description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(paddingSmall))

                RowWithPriceAndHasDrink(
                    price = product.price,
                    hasDrink = product.hasDrink,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun PillCardWithDropDownMenu(
    selectedOrder: PriceOrder,
    onOrderSelected: (PriceOrder) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val isSelected = selectedOrder != PriceOrder.FEATURED
    val options = listOf(
        PriceOrder.FEATURED to stringResource(id = R.string.order_featured),
        PriceOrder.ASCENDING to stringResource(id = R.string.price_low_to_high),
        PriceOrder.DESCENDING to stringResource(id = R.string.price_high_to_low)
    )

    Card(
        modifier = Modifier
            .padding(paddingSmall)
            .clickable { expanded = true },
        shape = Shapes.medium,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else Color.White
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = paddingMedium, vertical = paddingSmall)
        ) {
            BodyText(
                text = stringResource(id = com.iasiris.library.utils.R.string.order_by),
                color = if (isSelected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.width(paddingExtraSmall))

            Icon(
                imageVector = Icons.Default.Reorder,
                contentDescription = stringResource(id = R.string.dropwdown_menu),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(sizeMedium)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            options.forEach { (option, label) ->
                val selected = option == selectedOrder
                DropdownMenuItem(
                    text = {
                        BodyText(
                            text = label,
                            color = if (selected)
                                MaterialTheme.colorScheme.onPrimary
                            else
                                MaterialTheme.colorScheme.onSurface
                        )
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = if (selected)
                            MaterialTheme.colorScheme.onPrimary
                        else
                            MaterialTheme.colorScheme.onSurface
                    ),
                    onClick = {
                        expanded = false
                        onOrderSelected(option)
                    },
                    modifier = if (selected) Modifier
                        .background(MaterialTheme.colorScheme.primary)
                    else Modifier.background(Color.White)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCatalogPreview() {
    MuniAppTheme {
        ProductCatalog(
            navigateToProductDetail = { }
        )
    }
}