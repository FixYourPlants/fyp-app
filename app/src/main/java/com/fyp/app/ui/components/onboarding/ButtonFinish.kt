package com.fyp.app.ui.components.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonFinish(currentPage: Int, endPage: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = if (currentPage == endPage) Arrangement.Center else Arrangement.SpaceBetween
    ) {
        if (currentPage == endPage) {
            OutlinedButton(
                onClick = { onClick() }
            ) {
                Text(text = "Entrar", modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp), color = Color.Green)
            }
        }
    }
}