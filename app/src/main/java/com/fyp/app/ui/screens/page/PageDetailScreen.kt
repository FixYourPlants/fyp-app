package com.fyp.app.ui.screens.page

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.fyp.app.data.model.db.Page
import com.fyp.app.ui.components.BoxLongText
import com.fyp.app.ui.components.DetailBackground
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
@Destination
fun PageDetailsScreen(
    navigator: DestinationsNavigator,
    page: Page
) {
    DetailBackground {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .border(
                    width = 3.0.dp,
                    color = Color(59, 170, 0, 255),
                    shape = RoundedCornerShape(20.dp),
                )
        ){
            item {
                Box(
                    modifier = Modifier
                        .fillParentMaxHeight()
                        .background(Color(226, 237, 169, 255), shape = RoundedCornerShape(20.dp))
                        .zIndex(0f)
                ){
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        PageHeader(page)
                        PageContentSection(page)
                    }
                }
            }
        }
    }
}

@Composable
fun PageHeader(page: Page) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val date = inputFormat.parse(page.createdAt)
    val formattedDate = date?.let { outputFormat.format(it) } ?: page.createdAt

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(0.5f)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                AsyncImage(
                    model = page.imageUrl,
                    contentDescription = "Page Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = MaterialTheme.shapes.medium)
                        .border(width = 4.dp, color =  Color(59, 170, 0, 255), shape = MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(0.5f)) {
            Text(
                text = page.title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black
            )
            Text(
                text = "Fecha: $formattedDate",
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.Black
            )
        }
    }
}

@Composable
fun PageContentSection(page: Page) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Content",
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
    BoxLongText(text = page.content)
}
