package com.fyp.app.viewmodel.camera

import com.fyp.app.viewmodel.BaseViewModel

class UserCameraViewModel : CameraViewModel() {
}

object UserCameraViewModelImp : BaseViewModel<UserCameraViewModel>(UserCameraViewModel::class.java)