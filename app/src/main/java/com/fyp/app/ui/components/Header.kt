package com.fyp.app.ui.components

import TokenManager
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import coil.compose.AsyncImage
import com.fyp.app.BuildConfig
import com.fyp.app.R
import com.fyp.app.data.api.UserServiceImp
import com.fyp.app.data.model.db.User
import com.fyp.app.ui.screens.destinations.DiariesScreenDestination
import com.fyp.app.ui.screens.destinations.HistoryListScreenDestination
import com.fyp.app.ui.screens.destinations.HomeScreenDestination
import com.fyp.app.ui.screens.destinations.LoginScreenDestination
import com.fyp.app.ui.screens.destinations.UserDetailsScreenDestination
import com.fyp.app.ui.screens.destinations.AffectedSicknessListScreenDestination
import com.fyp.app.utils.UserPreferencesImp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(user: User?, onClickLogo: () -> Unit, onClickAccount: () -> Unit) {


    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Logo(onClickLogo)
                Text(
                    text = "Fix Your Plants",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 16.dp)
                        .width(200.dp)
                )
            }
        },
        actions = {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                if (user != null) {
                    if (user.imageUrl != null) {
                        AsyncImage(
                            model = BuildConfig.BACKEND_URL + user.imageUrl,
                            contentDescription = "Profile picture",
                            modifier = Modifier
                                .requiredSize(32.dp)
                                .clip(CircleShape)
                                .clickable { onClickAccount() },
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(painter = painterResource(id =R.drawable.default_user),
                            contentDescription = "Profile picture",
                            modifier = Modifier
                                .requiredSize(32.dp)
                                .clip(CircleShape)
                                .clickable { onClickAccount() },
                        )
                    }
                } else {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier.clickable { onClickAccount() }.testTag("login")
                    )
                }

            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(21, 230, 0, 255),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .border(width = 3.dp, color = Color(0, 120, 0))
    )
}


@Composable
fun HeaderSection(navigator: DestinationsNavigator) {
    var showDialog by remember { mutableStateOf(false) }

    val user: MutableState<User?> = remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        user.value = getLoggedInUserSafely()
    }

    Header(
        onClickLogo = { navigator.navigate(HomeScreenDestination()) },
        onClickAccount = {
            if (UserPreferencesImp.isAuthenticated()) {
                showDialog = true
            } else {
                navigator.navigate(LoginScreenDestination())
            }
        },
        user = user.value
    )

    if (showDialog and (user.value != null)) {
        Popup(alignment = Alignment.TopEnd, onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .padding(top = 48.dp, end = 16.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Row(modifier = Modifier
                        .clickable { navigator.navigate(UserDetailsScreenDestination(user.value!!)) }
                        .padding(8.dp)) {
                        Icon(
                            painterResource(id = R.drawable.user_details),
                            contentDescription = "Mi Perfil"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Mi Perfil")
                    }
                    HorizontalDivider(modifier = Modifier.width(128.dp))
                    Row(modifier = Modifier
                        .clickable { navigator.navigate(HistoryListScreenDestination()) }
                        .padding(8.dp)) {
                        Icon(
                            painterResource(id = R.drawable.history),
                            contentDescription = "Historial"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Historial")
                    }
                    HorizontalDivider(modifier = Modifier.width(128.dp))
                    Row(modifier = Modifier
                        .clickable { /* Acción para Cambiar Idioma */ }
                        .padding(8.dp)) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Cambiar Idioma")
                    }
                    HorizontalDivider(modifier = Modifier.width(128.dp))
                    Row(modifier = Modifier
                        .clickable { navigator.navigate(DiariesScreenDestination()) }
                        .padding(8.dp)) {
                        Icon(
                            painterResource(id = R.drawable.my_diaries),
                            contentDescription = "Mis Diarios"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Mis Diarios")
                    }
                    HorizontalDivider(modifier = Modifier.width(128.dp))
                    Row(modifier = Modifier
                        .clickable { navigator.navigate(AffectedSicknessListScreenDestination(user = user.value!!)) }
                        .padding(8.dp)) {
                        Icon(
                            painterResource(id = R.drawable.my_sickness),
                            contentDescription = "Mis Enfermedades",
                            Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Mis Enfermedades")
                    }
                    HorizontalDivider(modifier = Modifier.width(128.dp))
                    Row(modifier = Modifier
                        .clickable {
                            TokenManager.resetAllServices()
                            UserPreferencesImp.clear()
                            navigator.navigate(LoginScreenDestination())
                        }
                        .padding(8.dp)) {
                        Icon(
                            painterResource(id = R.drawable.logout),
                            contentDescription = "Cerrar Sesión"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Cerrar Sesión")
                    }
                }
            }
        }
    }
}


@Composable
fun HeaderInit(text: String) {
    Text(
        text = text,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 16.dp)
    )
}

suspend fun getLoggedInUserSafely(): User? {
    return try {
        UserServiceImp.getInstance().getLoggedInUser()
    } catch (e: Exception) {
        Log.e("HeaderSection", "Error loading logged-in user", e)
        null
    }
}
