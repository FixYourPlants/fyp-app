package com.fyp.app.ui.components.buttons

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ButtonLink(text: String, onClick: () -> Unit) {
    TextButton(onClick = onClick) {
        Text(text, color = Color(0xFF016723))
    }
}