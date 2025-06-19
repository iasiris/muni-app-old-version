package com.iasiris.feature.cart

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.iasiris.core.model.Product
import com.iasiris.library.utils.paddingExtraSmall
import com.iasiris.library.utils.paddingLarge
import com.iasiris.library.utils.paddingMedium
import com.iasiris.library.utils.paddingSmall
import com.iasiris.library.utils.ui.components.BackButtonConTitle
import com.iasiris.library.utils.ui.components.CaptionText
import com.iasiris.library.utils.ui.components.PrimaryButton
import com.iasiris.library.utils.ui.components.RowWithBodyTextAndAmount
import com.iasiris.library.utils.ui.components.RowWithNameAndDeleteIcon
import com.iasiris.library.utils.ui.components.RowWithPriceAndButtons
import com.iasiris.library.utils.ui.components.RowWithSubheadTextAndAmount
import com.iasiris.library.utils.ui.theme.MuniAppTheme

@Composable
fun Cart(
    navigateToCheckout: () -> Unit = {}, //TODO navegar Checkout final
    cartViewModel: CartViewModel = viewModel()
) {
    val cartUiState by cartViewModel.cartUiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            BackButtonConTitle(//TODO agregar logica para back button
                title = stringResource(id = R.string.cart_title),
                onBackButtonClick = { }
            )

            LazyColumn( //TODO fix scrollable content
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = paddingMedium)
                    .weight(1f)
            ) {
                itemsIndexed(cartUiState.products) { index, product ->
                    CardWithImageInTheLeft(
                        product = product,
                        onAdd = { cartViewModel.onAddProduct(product) },
                        onRemove = { cartViewModel.onRemoveProduct(product) },
                        onDelete = { cartViewModel.onDeleteProduct(product) }
                    )
                    if (index < cartUiState.products.lastIndex) {
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = paddingLarge, vertical = paddingExtraSmall),
                            color = MaterialTheme.colorScheme.outline,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(paddingSmall))

            Column(
                modifier = Modifier.padding(horizontal = paddingMedium)
            ) {
                RowWithBodyTextAndAmount(
                    text = stringResource(id = R.string.cart_subtotal),
                    totalAmount = cartUiState.subTotal
                )

                RowWithBodyTextAndAmount(
                    text = stringResource(id = R.string.delivery_fee),
                    totalAmount = cartUiState.deliveryFee
                )

                Spacer(modifier = Modifier.height(paddingSmall))

                RowWithSubheadTextAndAmount(
                    text = stringResource(id = R.string.cart_total),
                    totalAmount = cartUiState.totalAmount
                )

                Spacer(modifier = Modifier.height(paddingMedium))

                PrimaryButton( //TODO agregar proceso de checkout
                    label = stringResource(
                        id = R.string.checkout
                    ),
                    onClick = navigateToCheckout
                )

                Spacer(modifier = Modifier.height(paddingMedium))
            }


        }
    }
}

@Composable
fun CardWithImageInTheLeft(
    product: Product,
    onAdd: () -> Unit,
    onRemove: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceBright),
        modifier = Modifier
            .padding(paddingExtraSmall)
            .height(120.dp)
            .fillMaxWidth()
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
                RowWithNameAndDeleteIcon(
                    text = product.name,
                    onDelete = onDelete,
                )

                CaptionText(
                    text = product.description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                RowWithPriceAndButtons(
                    price = product.price,
                    quantity = product.quantity,
                    onAdd = onAdd,
                    onRemove = onRemove
                )
            }
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