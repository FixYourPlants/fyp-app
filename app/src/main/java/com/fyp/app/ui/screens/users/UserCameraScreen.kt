package com.fyp.app.ui.screens.users

import androidx.compose.runtime.Composable
import com.fyp.app.ui.components.Camera
import com.fyp.app.viewmodel.camera.UserCameraViewModelImp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun UserCameraScreen(navigator: DestinationsNavigator) {
    val cameraViewModel = UserCameraViewModelImp.getInstance()
    Camera { bitmap ->
        cameraViewModel.onTakePhoto(bitmap)
        cameraViewModel.onSelectBitmap(bitmap)
        navigator.navigateUp()
    }
}