package com.iasiris.feature.login.login

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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iasiris.feature.login.R
import com.iasiris.feature.login.register.RegisterBottomSheet
import com.iasiris.library.utils.paddingLarge
import com.iasiris.library.utils.paddingMedium
import com.iasiris.library.utils.paddingSmall
import com.iasiris.library.utils.ui.components.CaptionText
import com.iasiris.library.utils.ui.components.CustomTextField
import com.iasiris.library.utils.ui.components.CustomTextFieldPassword
import com.iasiris.library.utils.ui.components.PrimaryButton
import com.iasiris.library.utils.ui.theme.MuniAppTheme
import kotlinx.coroutines.launch

@Composable
fun Login(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    loginViewModel: LoginViewModel = viewModel()
) {
    val loginUiState by loginViewModel.loginUiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val invalidLogin = stringResource(id = R.string.invalid_login)

    if (loginUiState.isRegisterSheetVisible) {
        RegisterBottomSheet(
            navController,
            onDismiss = { loginViewModel.setShowRegistrationSheet(false) }
        )
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
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
                        contentDescription = stringResource(id = R.string.shopping_bag),
                    )

                    Spacer(modifier = Modifier.height(paddingMedium))

                    CustomTextField(
                        label = stringResource(id = R.string.email),
                        value = loginUiState.email,
                        onValueChange = loginViewModel::onEmailChange,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        errorMessage = loginUiState.emailError
                    )

                    Spacer(modifier = Modifier.height(paddingMedium))

                    CustomTextFieldPassword(
                        label = stringResource(id = R.string.password),
                        value = loginUiState.password,
                        onValueChange = loginViewModel::onPasswordChange,
                        passwordHidden = loginUiState.passwordHidden,
                        onVisibilityToggle = { loginViewModel.onPasswordIconClick() },
                        errorMessage = loginUiState.passwordError
                    )

                    Spacer(modifier = Modifier.height(paddingMedium))

                    Spacer(modifier = Modifier.height(paddingLarge))

                    PrimaryButton(
                        label = stringResource(id = R.string.login),
                        onClick = {
                            if (loginViewModel.onLogin()) {
                                navController.navigate("Home")
                            } else {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(invalidLogin)
                                }
                            }
                        },
                        enabled = loginUiState.isLoginEnabled
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
                    text = stringResource(id = R.string.do_not_have_account)
                )

                TextButton(
                    onClick = { loginViewModel.setShowRegistrationSheet(true) },
                    modifier = Modifier.wrapContentSize()
                ) {
                    CaptionText(
                        text = stringResource(id = R.string.sing_up_here),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
    MuniAppTheme {
        Login(
            navController = rememberNavController()
        )
    }
}