package com.iasiris.feature.profile

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.iasiris.core.model.User
import com.iasiris.library.utils.paddingExtraSmall
import com.iasiris.library.utils.paddingMedium
import com.iasiris.library.utils.paddingSmall
import com.iasiris.library.utils.ui.components.BackButtonConTitle
import com.iasiris.library.utils.ui.components.SubheadText
import com.iasiris.library.utils.ui.theme.MuniAppTheme

@Composable
fun Profile(
    profileViewModel: ProfileViewModel = viewModel()
) {
    val profileUiState by profileViewModel.profileUiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BackButtonConTitle(//TODO agregar logica para back button
                    title = stringResource(id = R.string.profile_title),
                    onBackButtonClick = { } //TODO
                )

                ProfileRow(
                    user = profileUiState.user,
                    onEditProfileClick = { /*TODO this takes you to select image*/ }
                )

                Spacer(modifier = Modifier.height(paddingMedium))

                val settingsOptions = listOf( //TODO this will be done in the viewModel
                    Pair("Profile", Icons.Default.PersonOutline),
                    Pair("Preferences", Icons.Default.Edit),
                    Pair("Security & Privacy", Icons.Default.AdminPanelSettings),
                    Pair("Support", Icons.Default.Help),
                    Pair("App Settings", Icons.Default.Settings)
                )
            }

        }
    }
}

@Composable
fun ProfileRow(
    user: User,
    onEditProfileClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = { onEditProfileClick }
        ) {
            AsyncImage(
                model = user.userImageUrl, //TODO will be taken from UI State
                contentDescription = stringResource(id = R.string.user_icon),
                onError = {
                    Log.i("AsyncImage", "Error loading image ${it.result.throwable.message}")
                },
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(Color.Cyan)
                    .height(300.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            )
        }

        Spacer(modifier = Modifier.width(paddingExtraSmall))

        Column {
            SubheadText(
                text = user.fullName,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
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