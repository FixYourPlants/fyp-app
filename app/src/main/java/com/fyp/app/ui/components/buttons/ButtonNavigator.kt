package com.fyp.app.ui.components.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun ButtonNavigator(navController: NavController? = null, route: String, text: String) {
    Button(
        onClick = {
            // Navegar a la otra pantalla cuando se hace clic en el botón
            navController?.navigate(text)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = route)
    }
}

@Preview
@Composable
fun ButtonNavigatorPreview() {
    ButtonNavigator(text = "Profile", route = "profile")
}