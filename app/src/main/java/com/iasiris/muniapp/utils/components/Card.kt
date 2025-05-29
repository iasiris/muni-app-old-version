package com.iasiris.muniapp.utils.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iasiris.muniapp.R
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
fun CustomCard(
    titulo: String,
    precio: String = "",
    descripcion: String = "",
    incluyeBebida: Boolean
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(paddingExtraSmall)
            .width(150.dp)
            .height(100.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(paddingSmall)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            BodyText(text = titulo)

            Spacer(modifier = Modifier.height(paddingExtraSmall))

            CaptionText(text = descripcion, color = MaterialTheme.colorScheme.onSurfaceVariant)

            Spacer(modifier = Modifier.height(paddingSmall))

            RowConPrecioyBebida(price = precio, incluyeBebida = incluyeBebida)
        }
    }
}

@Composable
fun RowConPrecioyBebida(
    price: String = "",
    incluyeBebida: Boolean
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
            imageVector = if (incluyeBebida) Icons.Filled.LocalBar else Icons.Outlined.NoDrinks,
            contentDescription = stringResource(id = R.string.bebida),
            tint = if (incluyeBebida) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
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
            CustomCard(
                titulo = "Milanesa",
                precio = "123",
                descripcion = "Loren Impsum",
                incluyeBebida = false
            )
            CustomCard(
                titulo = "Milanesa",
                precio = "123",
                descripcion = "Loren Impsum",
                incluyeBebida = true
            )
        }

    }
}