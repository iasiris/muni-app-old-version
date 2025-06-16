package com.iasiris.feature.home.productdetail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.iasiris.feature.home.R
import com.iasiris.library.utils.paddingMedium
import com.iasiris.library.utils.paddingSmall
import com.iasiris.library.utils.ui.components.BodyText
import com.iasiris.library.utils.ui.components.RowWithAddCartAndQuantity
import com.iasiris.library.utils.ui.components.RowWithPriceAndHasDrink
import com.iasiris.library.utils.ui.components.RowWithQuantityAndAmount
import com.iasiris.library.utils.ui.components.SubheadText

@Composable
fun ProductDetail(//TODO usar hilt para pasar id de producto
    prodId: String,
    navigateToCart: () -> Unit = {}, //TODO navegar a cart,
    prodDetailViewModel: ProductDetailViewModel = viewModel(
        factory = ProductDetailViewModelFactory(prodId)
    )
) {
    val prodDetailUiState by prodDetailViewModel.prodDetailUiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(), verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = paddingMedium)
            ) {
                AsyncImage(
                    model = prodDetailUiState.product.imageUrl,
                    contentDescription = stringResource(id = R.string.product_image),
                    onError = {
                        Log.i("AsyncImage", "Error loading image ${it.result.throwable.message}")
                    },
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                )

                Spacer(modifier = Modifier.height(paddingMedium))

                SubheadText(text = prodDetailUiState.product.name, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(paddingSmall))

                BodyText(
                    text = prodDetailUiState.product.description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(paddingSmall))

                RowWithPriceAndHasDrink(
                    price = prodDetailUiState.product.price,
                    hasDrink = prodDetailUiState.product.hasDrink,
                    FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = paddingMedium, end = paddingMedium, bottom = paddingMedium)
            ) {
                RowWithQuantityAndAmount(
                    quantity = prodDetailUiState.quantity,
                    totalAmount = prodDetailUiState.totalAmount
                )

                Spacer(modifier = Modifier.height(paddingMedium))

                RowWithAddCartAndQuantity(
                    quantity = prodDetailUiState.quantity,
                    onAdd = prodDetailViewModel::onAdd,
                    onRemove = prodDetailViewModel::onRemove,
                    navigateToCart = {
                        prodDetailViewModel::onAddToCart
                        navigateToCart()
                    })
            }

        }
    }
}