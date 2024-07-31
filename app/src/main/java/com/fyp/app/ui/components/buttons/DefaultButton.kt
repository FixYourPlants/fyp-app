package com.fyp.app.ui.components.buttons

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButton(onClick: () -> Unit, text: String) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(83, 155, 8, 255)),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .width(200.dp)
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}