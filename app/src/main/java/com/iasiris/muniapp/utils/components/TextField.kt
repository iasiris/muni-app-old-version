package com.iasiris.muniapp.utils.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
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
import com.iasiris.muniapp.R
import com.iasiris.muniapp.ui.theme.Shapes
import com.iasiris.muniapp.utils.paddingMedium

@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isPassword: Boolean = false
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
        keyboardOptions = if (isPassword) {
            KeyboardOptions(keyboardType = KeyboardType.Password)
        } else {
            keyboardOptions
        },
        visualTransformation = if (isPassword) {
            PasswordVisualTransformation()
        } else {
            visualTransformation
        },
        colors = colors
    )
}

@Composable
fun CustomSearchBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit
){
    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        placeholder = {BodyText(text = stringResource(id = R.string.buscar), color = MaterialTheme.colorScheme.onSurfaceVariant)},
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(id = R.string.busqueda_icon))
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
