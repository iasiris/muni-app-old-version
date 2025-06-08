package com.iasiris.muniapp.view.ui.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iasiris.muniapp.R
import com.iasiris.muniapp.navigation.Routes
import com.iasiris.muniapp.ui.theme.MuniAppTheme
import com.iasiris.muniapp.utils.components.CustomTextField
import com.iasiris.muniapp.utils.components.CustomTextFieldPassword
import com.iasiris.muniapp.utils.components.PrimaryButton
import com.iasiris.muniapp.utils.components.SubheadText
import com.iasiris.muniapp.utils.paddingExtraLarge
import com.iasiris.muniapp.utils.paddingMedium
import com.iasiris.muniapp.utils.paddingSmall

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterBottomSheet(
    navController: NavHostController,
    registerViewModel: RegisterViewModel = viewModel(),
    onDismiss: () -> Unit
) {
    val registerUiState by registerViewModel.registerUiState.collectAsStateWithLifecycle()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = paddingExtraLarge, vertical = paddingSmall)
        ) {
            SubheadText(
                text = stringResource(R.string.create_account),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(paddingMedium))

            CustomTextField(
                value = registerUiState.email,
                onValueChange = registerViewModel::onEmailChange,
                label = stringResource(id = R.string.email),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                errorMessage = registerUiState.emailError
            )

            Spacer(modifier = Modifier.height(paddingSmall))

            CustomTextField(
                value = registerUiState.fullName,
                onValueChange = registerViewModel::onFullNameChange,
                label = stringResource(id = R.string.full_name),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            )

            Spacer(modifier = Modifier.height(paddingSmall))

            CustomTextFieldPassword(
                value = registerUiState.password,
                onValueChange = registerViewModel::onPasswordChange,
                label = stringResource(id = R.string.password),
                passwordHidden = registerUiState.passwordHidden,
                onVisibilityToggle = { registerViewModel.onPasswordIconClick() },
                errorMessage = registerUiState.passwordError
            )

            Spacer(modifier = Modifier.height(paddingSmall))

            CustomTextFieldPassword(
                value = registerUiState.confirmPassword,
                onValueChange = registerViewModel::onConfirmPasswordChange,
                label = stringResource(id = R.string.confirm_password),
                passwordHidden = registerUiState.passwordConfirmHidden,
                onVisibilityToggle = { registerViewModel.onConfirmPasswordIconClick() },
                errorMessage = registerUiState.passwordConfirmError
            )

            Spacer(modifier = Modifier.height(paddingMedium))

            PrimaryButton(
                label = stringResource(id = R.string.sing_in),
                onClick = {
                    if (registerViewModel.onRegister()) {
                        navController.navigate(Routes.Home.name)
                    }
                },
                enabled = registerUiState.isRegisterEnabled
            )
            Spacer(modifier = Modifier.height(paddingMedium))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterPreview() {
    MuniAppTheme {
        RegisterBottomSheet(
            navController = rememberNavController(),
            onDismiss = { }
        )
    }
}
