package com.fyp.app.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ErrorMessage(errorMessage: String, showToast: MutableState<Boolean>) {
    Spacer(modifier = Modifier.height(8.dp))
    if (showToast.value) {
        Toast.makeText(
            LocalContext.current,
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()
        showToast.value = false
    }
}
