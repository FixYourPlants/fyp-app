package com.fyp.app.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun <T> LazyColumnList(
    items: List<T>,
    itemContent: @Composable (T) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(10.dp)
            .border(width = 2.dp, color = Color.Black)
    ) {
        items(items) { item ->
            itemContent(item)
        }
    }
}