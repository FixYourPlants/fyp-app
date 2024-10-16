package com.fyp.app.ui.components.buttons

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ClickableUrlText(url: String, text: String) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }
    val annotatedString = with(AnnotatedString.Builder()) {
        pushStringAnnotation("URL", url)
        append(text)
        toAnnotatedString()
    }

    ClickableText(text = annotatedString,
        style = TextStyle(
            color = Color(0xFF016723),
            fontWeight = FontWeight.Bold),
        onClick = {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        launcher.launch(intent)
    })
}