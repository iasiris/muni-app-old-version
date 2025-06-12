package com.iasiris.library.utils.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.iasiris.library.utils.R
import com.iasiris.library.utils.paddingMedium
import com.iasiris.library.utils.ui.theme.Shapes


@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    errorMessage: String? = null
) {
    val colors = TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
        unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
        focusedLabelColor = MaterialTheme.colorScheme.primary,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSurface
    )
    TextField(
        value = value,
        label = { BodyText(text = label) },
        modifier = modifier.fillMaxWidth(),
        onValueChange = onValueChange,
        singleLine = true,
        maxLines = 1,
        keyboardOptions = keyboardOptions,
        isError = errorMessage != null,
        supportingText = {
            errorMessage?.let {
                BodyText(text = it, color = Color.Red) //TODO CHECK THIS
            }
        },
        colors = colors
    )
}

@Composable
fun CustomTextFieldPassword(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    passwordHidden: Boolean,
    onVisibilityToggle: () -> Unit,
    errorMessage: String? = null
) {
    val colors = TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
        unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
        focusedLabelColor = MaterialTheme.colorScheme.primary,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSurface
    )
    TextField(
        value = value,
        label = { BodyText(text = label) },
        modifier = modifier.fillMaxWidth(),
        onValueChange = onValueChange,
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        colors = colors,
        trailingIcon = {
            IconButton(onClick = onVisibilityToggle) {
                val visibilityIcon =
                    if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordHidden)
                    stringResource(id = R.string.show_password)
                else
                    stringResource(id = R.string.hide_password)
                Icon(
                    imageVector = visibilityIcon,
                    contentDescription = description
                )
            }
        },
        isError = errorMessage != null,
        supportingText = {
            errorMessage?.let {
                BodyText(text = it, color = Color.Red)
            }
        },
    )
}

@Composable
fun CustomSearchBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit
) {
    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        placeholder = {
            BodyText(
                text = stringResource(id = R.string.search),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search_icon)
            )
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingMedium),
        shape = Shapes.large,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            unfocusedBorderColor = MaterialTheme.colorScheme.surfaceDim,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.surfaceDim,

            focusedContainerColor = Color.White,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedLeadingIconColor = MaterialTheme.colorScheme.primary
        )
    )
}
