package com.iasiris.feature.home

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalBar
import androidx.compose.material.icons.outlined.NoDrinks
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iasiris.library.utils.R
import com.iasiris.library.utils.paddingExtraSmall
import com.iasiris.library.utils.paddingSmall
import com.iasiris.library.utils.ui.components.BodyText
import com.iasiris.library.utils.ui.components.CaptionText
import com.iasiris.library.utils.ui.components.PrimaryButton
import com.iasiris.library.utils.ui.components.QuantityButtons
import com.iasiris.library.utils.ui.components.SubheadText


/*@Composable
fun ProductCatalog(
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

        }
    }
}

@Composable
fun CardWithImageInTheLeft(
    product: Product,
    quantity: Int,
    onAdd: () -> Unit = {},
    onRemove: () -> Unit = {},
    onAddToCart: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(paddingExtraSmall)
            .height(150.dp)
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
                modifier = Modifier.padding(end = paddingSmall)
            ) {
                BodyText(text = product.name)

                Spacer(modifier = Modifier.height(paddingExtraSmall))

                CaptionText(
                    text = product.description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(paddingSmall))

                RowConPriceAndHasDrink(price = product.price, hasDrink = product.hasDrink)

                Spacer(modifier = Modifier.height(paddingSmall))

                RowWithAddCartAndQuantity(
                    quantity = quantity,
                    onAdd = onAdd,
                    onRemove = onRemove,
                    onAddToCart = onAddToCart
                )
            }
        }
    }
}

@Composable
fun RowWithAddCartAndQuantity(
    quantity: Int,
    onAdd: () -> Unit = {},
    onRemove: () -> Unit = {},
    onAddToCart: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        QuantityButtons(
            quantity,
            onAdd = onAdd,
            onRemove = onRemove,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(paddingSmall))
        PrimaryButton(
            label = "Agregar",
            onClick = onAddToCart,
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        )
    }
}

@Composable
fun RowConPriceAndHasDrink(
    price: String = "",
    hasDrink: Boolean
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        SubheadText(
            text = "$$price",
            fontWeight = FontWeight.Medium
        )
        Icon(
            imageVector = if (hasDrink) Icons.Filled.LocalBar else Icons.Outlined.NoDrinks,
            contentDescription = stringResource(id = R.string.drink),
            tint = if (hasDrink) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}*/