package com.fyp.app.ui.screens.users

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fyp.app.R
import com.fyp.app.data.api.UserServiceImp
import com.fyp.app.data.model.db.User
import com.fyp.app.ui.components.BoxTag
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
@Destination
fun UserDetailsScreen(
    navigator: DestinationsNavigator,
) {
    val userState = remember { mutableStateOf<User?>(null) }
    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        Log.d("UserDetailsScreen", "Loading user...")
        val result = withContext(Dispatchers.IO) {
            try {
                UserServiceImp.getInstance().getLoggedInUser()
            } catch (e: Exception) {
                Log.e("UserDetailsScreen", "Error loading user", e)
                null
            }
        }
        userState.value = result
        isLoading.value = false
        Log.d("UserDetailsScreen", "User loaded")
        Log.d("UserDetailsScreen", "User: $result")
    }

    if (isLoading.value) {
        // Muestra un indicador de carga mientras se obtiene el usuario
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        userState.value?.let { user ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFFFFF))
                    .padding(2.dp)
                    .background(Color(0xFF000500))
                    .padding(2.dp)
                    .background(Color(0xFF4CAF50))
                    .padding(16.dp)
            ) {
                item {
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
                                // Fondo: Imagen de la planta
                                AsyncImage(
                                    model = user.imageUrl,
                                    placeholder = painterResource(id = R.drawable.down),
                                    error = painterResource(id = R.drawable.down_down),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(shape = MaterialTheme.shapes.medium),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(0.5f)) {
                            Text(
                                text = user.username,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                color = Color.Black
                            )
                            Text(
                                text = user.email,
                                fontStyle = FontStyle.Italic,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(vertical = 8.dp),
                                color = Color.Black
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Sobre mí...",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Black)
                            .padding(2.dp),
                        color = Color(0xFFA5FFA9)
                    ) {
                        Column {
                            Text(
                                text = user.aboutMe,
                                modifier = Modifier.padding(vertical = 4.dp),
                                color = Color.Black
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    user.favoritePlants?.let { plants ->
                        BoxTag(name = "Mis plantas:", values = plants.map { it.name })
                    }
                }
            }
        } ?: run {
            // Muestra un mensaje de error si el usuario no está disponible
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error al cargar el usuario", color = Color.Red)
            }
        }
    }


}
