package com.fyp.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BoxLongText(text: String, modifier: Modifier = Modifier, ) {
        Column {
            if (text.isEmpty()) {
                Text(
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 2.dp).background(Color(226, 237, 169, 255)),
                    text = "No data",
                    color = Color.Black
                )
            }
            else {
                Text(
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 2.dp).background(Color(226, 237, 169, 255)),
                    text = text,
                    color = Color.Black
                )
            }
        }
}
