package com.fyp.app.utils

import androidx.compose.ui.text.input.ImeAction

data class ValidatedTextFieldState(
    var value: String,
    val label: String,
    val imeAction: ImeAction,
    val minLines: Int,
    val onValueChange: (String) -> Unit
)