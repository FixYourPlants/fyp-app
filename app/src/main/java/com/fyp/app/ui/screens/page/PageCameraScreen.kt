package com.fyp.app.ui.screens.page

import androidx.compose.runtime.Composable
import com.fyp.app.ui.components.Camera
import com.fyp.app.viewmodel.camera.PageCameraViewModelImp
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun PageCameraScreen() {
    val cameraViewModel = PageCameraViewModelImp.getInstance()
    Camera { bitmap ->
        cameraViewModel.onTakePhoto(bitmap)
        cameraViewModel.onSelectBitmap(bitmap)
    }
}