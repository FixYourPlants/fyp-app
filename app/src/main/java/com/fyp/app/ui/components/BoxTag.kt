package com.fyp.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BoxTag(name: String, values: List<String>) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(Color(0xFF2E5805), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth()
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
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .background(Color(0xFF7CFC00), RoundedCornerShape(8.dp))
            .padding(vertical = 4.dp, horizontal = 8.dp)


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



@Preview(showBackground = true)
@Composable
fun BoxTagPreview() {
    BoxTag(
        name = "Características: ",
        values = listOf(
            "Hojas verdes",
            "Flores amarillas",
            "Hojas verdes",
            "Flores amarillas",
            "Hojas verdes",
            "Flores amarillas",
            "Hojas verdes",
            "Flores amarillas",
            "H"
        )
    )
}