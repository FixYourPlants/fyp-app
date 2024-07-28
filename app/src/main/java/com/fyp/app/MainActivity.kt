package com.fyp.app

import TokenManager
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fyp.app.ui.screens.NavGraphs
import com.fyp.app.utils.NotificationManager
import com.ramcosta.composedestinations.DestinationsNavHost
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DestinationsNavHost(navGraph = NavGraphs.root)
            TokenManager.startTokenRefreshTask(270, TimeUnit.SECONDS)

            val context = this

            NotificationManager.initialize(context)
            NotificationManager.scheduleNotificationTask(30, TimeUnit.MINUTES, context)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        TokenManager.stopTokenRefreshTask()
        NotificationManager.stopNotificationTask()
    }

    @Deprecated("Override to handle the result for permission request", ReplaceWith("onRequestPermissionsResult"), level = DeprecationLevel.WARNING)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == NotificationManager.PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permiso concedido, puedes proceder con la creación de notificaciones
                // Llama a la función que necesita crear notificaciones, si es necesario
                NotificationManager.createSimpleNotification(this, "Permiso Concedido", "Las notificaciones ahora están habilitadas")
            } else {
                // Permiso denegado, maneja el caso donde el permiso no fue concedido
                Log.e("MainActivity", "Permission for POST_NOTIFICATIONS was denied.")
            }
        }
    }
}