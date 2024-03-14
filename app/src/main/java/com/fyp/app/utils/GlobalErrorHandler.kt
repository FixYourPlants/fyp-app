package com.fyp.app.utils

import android.content.Context
import android.os.Looper
import android.widget.Toast

class GlobalErrorHandler(private val context: Context) : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        // Manejo de la excepción no capturada
        if (Looper.myLooper() == null) {
            Looper.prepare()
        }

        // Diferenciar entre diferentes tipos de errores
        when (throwable) {
            is NullPointerException -> {
                // Manejar NullPointerException
                showToast("Null Pointer Exception")
            }
            is IllegalArgumentException -> {
                // Manejar IllegalArgumentException
                showToast("Argument Exception")
            }
            else -> {
                // Manejar otros tipos de errores
                showToast("Error desconocido")
            }
        }

        // Detener la aplicación después de un breve retraso
        Thread.sleep(2000) // 2 segundos
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(10)
    }

    private fun showToast(message: String) {
        Toast.makeText(context, "Error: $message", Toast.LENGTH_LONG).show()
    }
}
