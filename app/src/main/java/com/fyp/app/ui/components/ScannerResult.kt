package com.fyp.app.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.fyp.app.BuildConfig
import com.fyp.app.data.model.db.History
import com.fyp.app.data.model.db.Plant
import com.fyp.app.data.model.db.Sickness

@Composable
fun ScannerResult(
    history: History,
    onClickPlant: () -> Unit = {},
    onClickSickness: () -> Unit = {}
) {
    var showDialog by remember { mutableStateOf(true) }
    val plant: Plant? = history.plant
    val sickness: Sickness? = history.sickness


    Log.d("ScannerResult", "Plant: $showDialog")
    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color(0xFFD8F3DC), shape = RoundedCornerShape(8.dp)) // Verde claro
            ) {
                if (plant != null)
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = plant.name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = plant.scientificName,
                            fontStyle = FontStyle.Italic,
                            fontSize = 12.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Log.d("ScannerResult", "Image: ${history.image}")
                        AsyncImage(model = BuildConfig.BACKEND_URL + history.image,
                            contentDescription = plant.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(150.dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .border(
                                    width = 1.0.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(8.dp),
                                )
                                .clickable { showDialog=false;onClickPlant() }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = plant.description,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        if (sickness != null)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        Color(0xFF2D6A4F),
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .padding(8.dp)
                                    .clickable { showDialog=false;onClickSickness() }
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Warning,
                                        contentDescription = "Alert Icon",
                                        tint = Color.White,
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Potential Disease: ${sickness.name}\n${sickness.description}",
                                        fontSize = 14.sp,
                                        color = Color.White
                                    )
                                }
                            }
                    }
            }
        }
    }
}