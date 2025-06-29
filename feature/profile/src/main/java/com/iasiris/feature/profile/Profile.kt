package com.iasiris.feature.profile

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.iasiris.library.utils.paddingExtraSmall
import com.iasiris.library.utils.paddingLarge
import com.iasiris.library.utils.paddingMedium
import com.iasiris.library.utils.ui.components.BackButtonWithTitle
import com.iasiris.library.utils.ui.components.CustomOutlinedTextField
import com.iasiris.library.utils.ui.components.CustomOutlinedTextFieldPassword
import com.iasiris.library.utils.ui.components.PrimaryButton
import com.iasiris.library.utils.ui.theme.MuniAppTheme

@Composable
fun Profile(
    profileViewModel: ProfileViewModel = viewModel()
) {
    val profileUiState by profileViewModel.profileUiState.collectAsStateWithLifecycle()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        profileViewModel.onImageSelected(uri)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BackButtonWithTitle(//TODO agregar logica para back button
                title = stringResource(id = R.string.profile_title),
                onBackButtonClick = { } //TODO
            )

            IconButton(
                onClick = { launcher.launch("image/*") }, //TODO check this logic
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Cyan) //TODO REPLACE WITH IMAGE

            ) {
                AsyncImage(
                    model = profileUiState.user.userImageUrl,
                    contentDescription = stringResource(id = R.string.user_icon),
                    onError = {
                        Log.i(
                            "AsyncImage",
                            "Error loading image ${it.result.throwable.message}"
                        )
                    },
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(paddingMedium))

            CustomOutlinedTextField(
                label = stringResource(id = R.string.email_label),
                text = profileUiState.user.email,
                onValueChange = { profileViewModel::onEmailChange },
                leadingIcon = Icons.Default.Email,
            )

            CustomOutlinedTextFieldPassword(
                label = stringResource(id = R.string.password),
                text = profileUiState.user.password,
                onValueChange = { profileViewModel::onPasswordChange },
                leadingIcon = Icons.Default.Password,
                passwordHidden = profileUiState.passwordHidden,
                onVisibilityToggle = { profileViewModel.onPasswordIconClick() },
            )

            CustomOutlinedTextField(
                label = stringResource(id = R.string.fullname_label),
                text = profileUiState.user.fullName,
                onValueChange = { profileViewModel.onFieldChange(ProfileField.FullName, it) },
                leadingIcon = Icons.Default.PersonOutline,
            )

            CustomOutlinedTextField(
                label = stringResource(id = R.string.nationality_label),
                text = profileUiState.user.nationality,
                onValueChange = { profileViewModel.onFieldChange(ProfileField.Nationality, it) },
                leadingIcon = Icons.Default.Public
            )

            Spacer(modifier = Modifier.height(paddingLarge))

            PrimaryButton(
                label = stringResource(id = R.string.save_changes),
                onClick = { profileViewModel::onSaveChanges },
                modifier = Modifier
                    .padding(horizontal = paddingExtraSmall),
                enabled = profileUiState.isSaveEnabled
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    MuniAppTheme {
        Profile()
    }
}