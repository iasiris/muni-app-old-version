package com.iasiris.muniapp.view.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iasiris.muniapp.R
import com.iasiris.muniapp.ui.theme.MuniAppTheme
import com.iasiris.muniapp.utils.components.CustomTextField
import com.iasiris.muniapp.utils.components.OverlainText
import com.iasiris.muniapp.utils.components.PrimaryButton
import com.iasiris.muniapp.utils.paddingExtraLarge
import com.iasiris.muniapp.utils.paddingExtraSmall
import com.iasiris.muniapp.utils.paddingMedium
import com.iasiris.muniapp.utils.size12dp

@Composable
fun Register(modifier: Modifier = Modifier) {
    // TODO: Move to ViewModel
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var nombre by rememberSaveable { mutableStateOf("") }
    var apellido by rememberSaveable { mutableStateOf("") }
    var direccion by rememberSaveable { mutableStateOf("") }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = paddingMedium),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(vertical = paddingMedium)
            ) {
                item {
                    Image(
                        painter = painterResource(id = R.drawable.muni_icon),
                        contentDescription = stringResource(id = R.string.bolsas_compra)
                    )

                    Spacer(modifier = Modifier.height(paddingExtraLarge))

                    CustomTextField(
                        label = stringResource(id = R.string.email),
                        value = email,
                        onValueChange = { email = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )

                    Spacer(modifier = Modifier.height(paddingMedium))

                    CustomTextField(
                        label = stringResource(id = R.string.password),
                        value = password,
                        onValueChange = { password = it },
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(paddingMedium))

                    CustomTextField(
                        label = stringResource(id = R.string.nombre),
                        value = nombre,
                        onValueChange = { nombre = it },
                        keyboardOptions = KeyboardOptions.Default
                    )

                    Spacer(modifier = Modifier.height(paddingMedium))

                    CustomTextField(
                        label = stringResource(id = R.string.apellido),
                        value = apellido,
                        onValueChange = { apellido = it },
                        keyboardOptions = KeyboardOptions.Default
                    )

                    Spacer(modifier = Modifier.height(paddingMedium))

                    CustomTextField(
                        label = stringResource(id = R.string.direccion),
                        value = direccion,
                        onValueChange = { direccion = it },
                        keyboardOptions = KeyboardOptions.Default
                    )

                    Spacer(modifier = Modifier.height(paddingMedium))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RecuerdameComponent()

                        Spacer(modifier = Modifier.weight(1f))

                        TextButton(
                            onClick = { /* TODO */ },
                            modifier = Modifier.wrapContentSize()
                        ) {
                            OverlainText(
                                text = stringResource(id = R.string.olvidaste_contraseña)
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = paddingMedium, vertical = paddingMedium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PrimaryButton(
                    label = stringResource(id = R.string.crear_cuenta),
                    onClick = {}
                )
            }
        }
    }
}

@Composable
fun RecuerdameComponent() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { /*TODO*/}
    ) {
        var isChecked by remember { mutableStateOf(false) }

        Box(modifier = Modifier
            .size(size12dp)
            .background(
                color = if (isChecked) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = MaterialTheme.shapes.small
            )
            .clickable { isChecked = !isChecked }
            .border(
                width = 1.dp,
                color = if (isChecked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                shape = MaterialTheme.shapes.small
            ))

        Spacer(modifier = Modifier.width(paddingExtraSmall))

        OverlainText(
            text = stringResource(id = R.string.recuerdame),
            modifier = Modifier.clickable { isChecked = !isChecked }
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
    MuniAppTheme {
        Register(Modifier.padding(paddingMedium))
    }
}
