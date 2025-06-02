package com.iasiris.muniapp.view.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iasiris.muniapp.R
import com.iasiris.muniapp.navigation.Routes
import com.iasiris.muniapp.ui.theme.MuniAppTheme
import com.iasiris.muniapp.utils.components.CaptionText
import com.iasiris.muniapp.utils.components.CustomTextField
import com.iasiris.muniapp.utils.components.OverlainText
import com.iasiris.muniapp.utils.components.PrimaryButton
import com.iasiris.muniapp.utils.components.SubheadText
import com.iasiris.muniapp.utils.paddingExtraLarge
import com.iasiris.muniapp.utils.paddingExtraSmall
import com.iasiris.muniapp.utils.paddingLarge
import com.iasiris.muniapp.utils.paddingMedium
import com.iasiris.muniapp.utils.paddingSmall
import com.iasiris.muniapp.utils.size12dp

@Composable
fun Login(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: LoginViewModel = viewModel()
) {
    if (viewModel.showRegistrationSheet) {
        RegistrationBottomSheet(
            navController,
            viewModel = viewModel,
            onDismiss = { viewModel.onShowRegistrationSheet(false) }
        )
    }

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
                        value = viewModel.loginState.email,
                        onValueChange = { viewModel.onLoginEmailChange(it) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )

                    Spacer(modifier = Modifier.height(paddingMedium))

                    CustomTextField(
                        label = stringResource(id = R.string.password),
                        value = viewModel.loginState.password,
                        onValueChange = { viewModel.onLoginPasswordChange(it) },
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(paddingMedium))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RecuerdameComponent() //TODO agregar funcionamiento

                        Spacer(modifier = Modifier.weight(1f))

                        TextButton(
                            onClick = { /* TODO */ },
                            modifier = Modifier.wrapContentSize() //TODO agregar funcionamiento
                        ) {
                            OverlainText(
                                text = stringResource(id = R.string.olvidaste_contraseña)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(paddingLarge))

                    PrimaryButton(
                        label = stringResource(id = R.string.ingresar),
                        onClick = {
                            if (viewModel.onLogin()) {
                                navController.navigate(Routes.Home.name)
                            }//TODO agregar else con notification de error
                        }
                    )
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
                    onClick = { viewModel.onShowRegistrationSheet(true) },
                    modifier = Modifier.wrapContentSize()
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

@Composable
fun RecuerdameComponent() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { /*TODO*/ }
    ) {
        var isChecked by remember { mutableStateOf(false) }

        Box(modifier = Modifier
            .size(size12dp)
            .background(
                color = if (isChecked) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = MaterialTheme.shapes.small
            )
            .clickable {
                isChecked = !isChecked
            }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationBottomSheet( //TODO el teclado deberia llevarte al siguiente item
    navController: NavHostController,
    viewModel: LoginViewModel,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = paddingExtraLarge, vertical = paddingSmall)
        ) {
            SubheadText(
                text = stringResource(R.string.crear_cuenta),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(paddingMedium))

            CustomTextField(
                value = viewModel.registrationState.email,
                onValueChange = viewModel::onRegistrationEmailChange,
                label = stringResource(id = R.string.email),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )

            Spacer(modifier = Modifier.height(paddingSmall))

            CustomTextField(
                value = viewModel.registrationState.fullName,
                onValueChange = viewModel::onRegistrationFullNameChange,
                label = stringResource(id = R.string.nombre_completo),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            )

            Spacer(modifier = Modifier.height(paddingSmall))

            CustomTextField(
                value = viewModel.registrationState.password,
                onValueChange = viewModel::onRegistrationPasswordChange,
                label = stringResource(id = R.string.password),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isPassword = true
            )

            Spacer(modifier = Modifier.height(paddingSmall))

            CustomTextField(
                value = viewModel.registrationState.confirmPassword,
                onValueChange = viewModel::onRegistrationConfirmPasswordChange,
                label = stringResource(id = R.string.confirmar_password),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isPassword = true
            )

            Spacer(modifier = Modifier.height(paddingMedium))

            PrimaryButton(
                label = stringResource(id = R.string.registrate),
                onClick = {
                    if(viewModel.onRegister()){
                        navController.navigate(Routes.Home.name)
                    }
                }
            )
            Spacer(modifier = Modifier.height(paddingMedium))
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SingInPreview() {
    MuniAppTheme {
        Login(
            Modifier.padding(paddingMedium),
            navController = rememberNavController()
        )
    }
}