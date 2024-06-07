package com.fyp.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
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
                containerColor = Color(0xFFA5FFA9),
                errorContainerColor = Color(0xFFA5FFA9)
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

