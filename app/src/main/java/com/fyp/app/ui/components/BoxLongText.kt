package com.fyp.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BoxLongText(text: String, modifier: Modifier = Modifier, ) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(2.dp),
        color = Color(0xFFA5FFA9)
    ) {
        Column {
            if (text.isEmpty()) {
                Text(
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 2.dp),
                    text = "No data",
                    color = Color.Black
                )
            }
            else {
                Text(
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 2.dp),
                    text = text,
                    color = Color.Black
                )
            }
        }
    }
}
