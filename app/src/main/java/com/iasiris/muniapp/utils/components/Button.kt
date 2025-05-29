package com.iasiris.muniapp.utils.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iasiris.muniapp.R
import com.iasiris.muniapp.ui.theme.MuniAppTheme
import com.iasiris.muniapp.utils.paddingSmall

@Composable
fun PrimaryButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Button(
        onClick = { onClick },
        modifier = modifier
    ) {
        ButtonText(text = label)
    }
}

@Composable
fun SecondaryButton(
    label: String,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        ButtonText(
            text = label,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun BackButtonConTitulo(
    title: String,
    onBackButtonClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = paddingSmall)
    ) {
        IconButton(
            onClick = { onBackButtonClick() },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = stringResource(id = R.string.back_button),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

        SubheadText(
            text = title,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButtons() {
    MuniAppTheme {
        Column {
            PrimaryButton(
                label = "Enviar",
                onClick = {}
            )

            SecondaryButton(
                label = "Enviar",
                onClick = {}
            )

            BackButtonConTitulo(
                title = "Titulo",
                onBackButtonClick = {}
            )
        }
    }
}