package com.iasiris.muniapp.utils.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iasiris.muniapp.R
import com.iasiris.muniapp.data.model.Product
import com.iasiris.muniapp.ui.theme.MuniAppTheme
import com.iasiris.muniapp.ui.theme.Shapes
import com.iasiris.muniapp.utils.paddingExtraSmall
import com.iasiris.muniapp.utils.paddingMedium
import com.iasiris.muniapp.utils.paddingSmall

@Composable
fun PillCard(texto: String) {
    Card(
        modifier = Modifier
            .padding(paddingSmall)
            .clickable { /* TODO Handle click */ },
        shape = Shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(paddingSmall),
            modifier = Modifier.padding(paddingSmall)
        ) {
            BodyText(text = texto)
        }
    }
}

@Composable
fun CustomCard( //Cada ítem debe tener un botón para agregar al carrito y seleccionar cantidad.
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
            .defaultMinSize(300.dp, 150.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(paddingSmall)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            BodyText(text = product.name)

            Spacer(modifier = Modifier.height(paddingExtraSmall))

            CaptionText(
                text = product.description,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(paddingSmall))

            RowConPriceAndHasDrink(price = product.price, hasDrink = product.hasDrink)

            RowWithAddCartAndQuantity(
                quantity = quantity,
                onAdd = onAdd,
                onRemove = onRemove,
                onAddToCart = onAddToCart
            )
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
            Image(
                painter = painterResource(id = product.image.toInt()), //TODO usar Coil para managear imagenes
                contentDescription = "",
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
}

@Preview(showBackground = true)
@Composable
fun CardsPreview() {
    MuniAppTheme {
        Column(
            modifier = Modifier.padding(paddingMedium)
        ) {
            PillCard(
                texto = "Mountain"
            )
            CardWithImageInTheLeft(
                Product("Milanesa", "Loren Impsum", "123", false, ""),
                quantity = 1,
                onAdd = {},
                onRemove = {},
                onAddToCart = {}
            )
        }

    }
}