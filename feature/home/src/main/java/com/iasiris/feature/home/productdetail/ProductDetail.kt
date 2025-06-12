package com.iasiris.feature.home.productdetail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.iasiris.feature.home.R
import com.iasiris.library.utils.paddingExtraSmall
import com.iasiris.library.utils.paddingMedium
import com.iasiris.library.utils.paddingSmall
import com.iasiris.library.utils.ui.components.BodyText
import com.iasiris.library.utils.ui.components.RowWithAddCartAndQuantity
import com.iasiris.library.utils.ui.components.RowWithPriceAndHasDrink
import com.iasiris.library.utils.ui.components.RowWithQuantityAndTotalAmount
import com.iasiris.library.utils.ui.components.SubheadText
import com.iasiris.library.utils.ui.theme.MuniAppTheme

@Composable
fun ProductDetail( //TODO pasar por nav un producto y conectar prod con viewModel
    modifier: Modifier = Modifier, navController: NavHostController, //TODO navegar a cart
    prodDetailViewModel: ProductDetailViewModel = viewModel()
) {
    val prodDetailUiState by prodDetailViewModel.prodDetailUiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
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
                )

                SubheadText(text = prodDetailUiState.product.name, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(paddingExtraSmall))

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
                    .padding(horizontal = paddingMedium)
            ) {
                RowWithQuantityAndTotalAmount(
                    quantity = prodDetailUiState.quantity,
                    totalAmount = prodDetailUiState.totalAmount
                )
                Spacer(modifier = Modifier.height(paddingMedium))
                RowWithAddCartAndQuantity(
                    quantity = prodDetailUiState.quantity,
                    onAdd = prodDetailViewModel::onAdd,
                    onRemove = prodDetailViewModel::onRemove,
                    onAddToCart = { prodDetailViewModel.onAddToCart() })
            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductDetailPreview() {
    MuniAppTheme {
        ProductDetail(
            navController = rememberNavController()
        )
    }
}