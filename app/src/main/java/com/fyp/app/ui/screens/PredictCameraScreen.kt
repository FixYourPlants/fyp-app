package com.fyp.app.ui.screens

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.fyp.app.R
import com.fyp.app.ui.components.Camera
import com.fyp.app.viewmodel.camera.PredictCameraViewModelImp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun PredictCameraScreen(navigator: DestinationsNavigator) {
    val cameraViewModel = PredictCameraViewModelImp.getInstance()
    val context = LocalContext.current
    Camera { bitmap ->
        cameraViewModel.onTakePhoto(BitmapFactory.decodeResource(context.resources, R.drawable.apple))
        cameraViewModel.onSelectBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.apple))
        navigator.navigateUp()
    }
}