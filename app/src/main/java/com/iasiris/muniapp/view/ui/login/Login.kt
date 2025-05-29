package com.iasiris.muniapp.view.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.iasiris.muniapp.R
import com.iasiris.muniapp.ui.theme.MuniAppTheme
import com.iasiris.muniapp.utils.components.CaptionText
import com.iasiris.muniapp.utils.components.CustomTextField
import com.iasiris.muniapp.utils.components.OverlainText
import com.iasiris.muniapp.utils.components.PrimaryButton
import com.iasiris.muniapp.utils.paddingLarge
import com.iasiris.muniapp.utils.paddingMedium
import com.iasiris.muniapp.utils.paddingSmall
import com.iasiris.muniapp.view.ui.register.RecuerdameComponent

@Composable
fun Login(modifier: Modifier = Modifier) {
    //TODO this should be in viewModel
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Card(
                modifier = Modifier
                    .padding(horizontal = paddingSmall, vertical = paddingLarge),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(paddingLarge),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.muni_icon),
                        contentDescription = stringResource(id = R.string.bolsas_compra),
                    )

                    Spacer(modifier = Modifier.height(paddingMedium))

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

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RecuerdameComponent()

                        Spacer(modifier = Modifier.weight(1f))

                        TextButton(
                            onClick = { /* TODO */ }, modifier = Modifier.wrapContentSize()
                        ) {
                            OverlainText(
                                text = stringResource(id = R.string.olvidaste_contraseña)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(paddingLarge))

                    PrimaryButton(label = stringResource(id = R.string.ingresar), onClick = {})
                }
            }

            Spacer(modifier = Modifier.height(paddingLarge))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CaptionText(
                    text = stringResource(id = R.string.no_tienes_cuenta)
                )

                TextButton(
                    onClick = { /* TODO */ }, modifier = Modifier.wrapContentSize()
                ) {
                    CaptionText(
                        text = stringResource(id = R.string.registrate_aqui),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SingInPreview() {
    MuniAppTheme {
        Login(Modifier.padding(paddingMedium))
    }
}