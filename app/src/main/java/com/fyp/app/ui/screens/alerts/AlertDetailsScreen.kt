package com.fyp.app.ui.screens.alerts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.fyp.app.R
import com.fyp.app.data.model.db.Alert
import com.fyp.app.ui.components.BoxLongText
import com.fyp.app.ui.components.DetailBackground
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun AlertDetailsScreen(
    navigator: DestinationsNavigator,
    alert: Alert
) {
    DetailBackground {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .paint(
                    painterResource(id = R.drawable.plagues_top),
                    alignment = AbsoluteAlignment.TopRight
                )
                .border(
                    width = 3.0.dp,
                    color = Color(59, 170, 0, 255),
                    shape = RoundedCornerShape(20.dp),
                )
        ) {
            item {
                Spacer(modifier = Modifier
                    .height(130.dp)
                    .fillMaxWidth())
                Box(
                    modifier = Modifier
                        .fillParentMaxHeight()
                        .background(Color(226, 237, 169, 255), shape = RoundedCornerShape(20.dp))
                        .zIndex(0f)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        AlertHeader(alert = alert)
                    }
                }
            }
        }
    }

}

@Composable
fun AlertHeader(alert: Alert) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-80).dp),
        contentAlignment = Alignment.TopCenter
    ) {
        AsyncImage(
            model = alert.image,
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .border(
                    width = 2.0.dp,
                    color = Color.Black,
                    shape = MaterialTheme.shapes.medium,
                )
                .zIndex(2f),
            contentScale = ContentScale.Crop
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-70).dp),
        contentAlignment = Alignment.TopCenter) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = alert.title,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.Black,
            )
            Text(
                text = alert.family,
                fontStyle = FontStyle.Italic,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Especies afectadas: ",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = alert.host,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Distribución: ",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = alert.distribution,
                color = Color.Black
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Daños Provocados",
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 20.sp,
        modifier = Modifier
            .fillMaxWidth()
    )
    HorizontalDivider(
        thickness = 2.dp,
        color = Color(59, 170, 0, 255),
        modifier = Modifier.padding(4.dp)
    )
    BoxLongText(text = alert.damage)
}