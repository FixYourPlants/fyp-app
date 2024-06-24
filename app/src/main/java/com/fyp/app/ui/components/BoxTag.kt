package com.fyp.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// TODO: Separa en un futuro en distintas clases que permitan añadir acciones si se pulsa uno de los botnoes
@OptIn(ExperimentalLayoutApi::class)
@Composable

fun BoxTag(name: String, values: List<String>) {
    Column(
        modifier = Modifier
            .background(Color(0xA6349E06), shape = RoundedCornerShape(8.dp))
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            values.forEach { value ->
                BoxContent(value = value)
            }
        }
    }
}


@Composable
fun BoxContent(value: String) {
    Box(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 4.dp)
            .background(Color(0xFF7CFC00), RoundedCornerShape(8.dp))
            .padding(vertical = 4.dp, horizontal = 4.dp)


    ) {
        Text(
            text = value,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}