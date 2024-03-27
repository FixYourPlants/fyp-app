package com.fyp.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.app.R
import com.fyp.app.ui.components.buttons.LoginGoogleButton

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Login image",
            modifier = Modifier
                .fillMaxSize(0.3f)
                .clip(CircleShape)
                .border(2.dp, Color.Black, CircleShape),
            contentScale = ContentScale.Crop

        )
        Text(text = "Welcome Back", fontSize = 28.sp, fontWeight = FontWeight.Bold,modifier = Modifier.padding(16.dp))
        
        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Login to your account")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Email address") })

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Password") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /*TODO*/ }) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(18.dp))

        TextButton(onClick = { /*TODO*/ }) {
            Text("Don't have an account? Sign up")
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(text = "Or sign in with")

        Spacer(modifier = Modifier.height(4.dp))

        LoginGoogleButton()
    }

}

@Composable
@Preview
fun LoginScreenPreview() {
    LoginScreen()
}