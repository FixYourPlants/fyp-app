package com.fyp.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class TextFieldError(
    val isError: Boolean,
    val errorMessage: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValidatedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    errors: List<TextFieldError>, // Usamos una lista de TextFieldError
    imeAction: ImeAction,
    minLines: Int = 1,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(vertical = 4.dp)) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            isError = errors.any { it.isError },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (errors.any { it.isError }) MaterialTheme.colorScheme.error else Color.Black,
                focusedTextColor = if (errors.any { it.isError }) MaterialTheme.colorScheme.error else Color.Black,
                unfocusedBorderColor = if (errors.any { it.isError }) MaterialTheme.colorScheme.error else Color.Black,
                unfocusedTextColor = if (errors.any { it.isError }) MaterialTheme.colorScheme.error else Color.Black,
                focusedLabelColor = if (errors.any { it.isError }) MaterialTheme.colorScheme.error else Color.Black,
                unfocusedLabelColor = if (errors.any { it.isError }) MaterialTheme.colorScheme.error else Color.Black,
                cursorColor = Color.Black,
                containerColor = Color.White,
                errorContainerColor = Color.White
            ),
            minLines = minLines,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        errors.forEach { error ->
            if (error.isError) {
                Text(
                    text = error.errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValidatedTextFieldLoginRegister(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    errors: List<TextFieldError>, // Usamos una lista de TextFieldError
    imeAction: ImeAction,
    minLines: Int = 1,
    fraction: Float = 1f,
    secret: Boolean = false,
    modifier: Modifier = Modifier,
) {
    var passwordVisible by remember { mutableStateOf(!secret) }

    Column(modifier = modifier.padding(vertical = 4.dp)) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            isError = errors.any { it.isError },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = imeAction,
                keyboardType = if (secret) KeyboardType.Password else KeyboardType.Text
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                if (secret) {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = description)
                    }
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (errors.any { it.isError }) MaterialTheme.colorScheme.error else Color.Black,
                focusedTextColor = if (errors.any { it.isError }) MaterialTheme.colorScheme.error else Color.Black,
                unfocusedBorderColor = if (errors.any { it.isError }) MaterialTheme.colorScheme.error else Color.Black,
                unfocusedTextColor = if (errors.any { it.isError }) MaterialTheme.colorScheme.error else Color.Black,
                focusedLabelColor = if (errors.any { it.isError }) MaterialTheme.colorScheme.error else Color.Black,
                unfocusedLabelColor = if (errors.any { it.isError }) MaterialTheme.colorScheme.error else Color.Black,
                cursorColor = Color.Black,
                containerColor = Color.White,
                errorContainerColor = Color.White
            ),
            minLines = minLines,
            modifier = modifier
                .fillMaxWidth(fraction)
                .padding(vertical = 8.dp)
        )
        errors.forEach { error ->
            if (error.isError) {
                Text(
                    text = error.errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
        }
    }
}


@Preview
@Composable
fun ValidatedTextFieldPreview() {
    ValidatedTextFieldLoginRegister(
        value = "",
        onValueChange = {},
        label = "Label",
        errors = listOf(TextFieldError(true, "Error message")),
        imeAction = ImeAction.Done
    )
}

