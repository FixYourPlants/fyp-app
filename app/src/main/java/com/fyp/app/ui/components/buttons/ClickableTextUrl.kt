package com.fyp.app.ui.components.buttons

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString

@Composable
fun ClickableUrlText(url: String, text: String) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }
    val annotatedString = with(AnnotatedString.Builder()) {
        pushStringAnnotation("URL", url)
        append(text)
        toAnnotatedString()
    }

    ClickableText(text = annotatedString, onClick = {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        launcher.launch(intent)
    })
}