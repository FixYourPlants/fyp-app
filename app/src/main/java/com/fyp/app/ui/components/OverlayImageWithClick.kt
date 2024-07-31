package com.fyp.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fyp.app.R

@Composable
public fun OverlayImageWithClick(
    defaultImageUrl: String,
    clickedImageUrl: Int,
    onClick: () -> Unit
) {
    var isImageClicked by remember { mutableStateOf(false) }

    Box(){
        AsyncImage(
            model = defaultImageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .border(
                    width = 2.0.dp,
                    color = Color.Black,
                    shape = MaterialTheme.shapes.medium,
                ),
            contentScale = ContentScale.Crop
        )

        // Frente: Imagen superpuesta con cambio de imagen al hacer clic
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .clipToBounds()
                .clickable {
                    onClick()
                    isImageClicked = !isImageClicked
                },
            contentAlignment = Alignment.TopStart
        ) {
            // Ajusta el tamaño y la posición de la imagen superpuesta
            val scaleX: Float = if (clickedImageUrl == R.drawable.hearth) 1.9F else 1.0F
            val scaleY:Float  = if (clickedImageUrl == R.drawable.hearth) 1.9F else 1.0F

            val offsetX = if (clickedImageUrl == R.drawable.hearth) 6.dp else 12.dp
            val offsetY  = if (clickedImageUrl == R.drawable.hearth) 6.dp else 12.dp

            Image(
                painter = painterResource(id = clickedImageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp)
                    .scale(scaleX = scaleX, scaleY= scaleY)
                    .offset(offsetX, offsetY),  // Ajusta la posición según tus necesidades
                contentScale = ContentScale.Fit
            )
        }
    }
}
