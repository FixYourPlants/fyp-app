package com.fyp.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fyp.app.R

@Composable
fun Logo(onClickLogo: () -> Unit) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(Color.Black, CircleShape)
            .padding(4.dp)
            .background(Color.White, CircleShape)
            .clickable { onClickLogo() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(32.dp),
        )
    }
}

@Composable
fun LogoInit() {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Logo",
        modifier = Modifier
            .requiredSize(100.dp)
            .clip(CircleShape)
            .border(2.dp, Color.Black, CircleShape),
        contentScale = ContentScale.Crop
    )
}