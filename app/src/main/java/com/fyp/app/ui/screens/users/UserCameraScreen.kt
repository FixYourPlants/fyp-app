package com.fyp.app.ui.screens.users

import androidx.compose.runtime.Composable
import com.fyp.app.ui.components.Camera
import com.fyp.app.viewmodel.CameraViewModelImp
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun UserCameraScreen() {
    val cameraViewModel = CameraViewModelImp.getInstance()
    Camera { bitmap ->
        cameraViewModel.onTakePhoto(bitmap)
        cameraViewModel.onSelectBitmap(bitmap)
    }
}
