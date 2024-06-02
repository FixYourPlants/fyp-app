package com.fyp.app.ui.components.image

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        AsyncImage(
            model = defaultImageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )

        // Frente: Imagen superpuesta con cambio de imagen al hacer clic
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = MaterialTheme.shapes.medium)
                .clickable {
                    onClick()
                },
        ) {
            // Ajusta el tamaño y la posición de la imagen superpuesta
            val imageSize = if (isImageClicked) 150.dp else 50.dp
            Image(
                painter = painterResource(id = clickedImageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(imageSize)
                    .offset(16.dp, 16.dp),  // Ajusta la posición según tus necesidades
                contentScale = ContentScale.Fit
            )
        }
    }
}
