package com.fyp.app.ui.screens.users

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.fyp.app.data.model.db.User
import com.fyp.app.ui.components.DetailBackground
import com.fyp.app.ui.screens.FormScreenContent
import com.fyp.app.ui.screens.destinations.UserCameraScreenDestination
import com.fyp.app.ui.screens.destinations.UserDetailsScreenDestination
import com.fyp.app.viewmodel.UserEditViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Composable
@Destination
fun UserEditScreen(
    navigator: DestinationsNavigator,
    user: User,
) {
    val viewModel = remember { UserEditViewModel(user) }
    val updatedImages by viewModel.cameraViewModel.bitmaps.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    // TODO: Gestionar las imagenes.
    // viewModel.cameraViewModel.clearBitmaps()
    // viewModel.cameraViewModel.clearSelectedBitmap()
    DetailBackground {
        FormScreenContent(
            title = "Editar usuario",
            firstImage = user.imageUrl,
            updatedImages = updatedImages,
            onAddImageClick = { navigator.navigate(UserCameraScreenDestination) },
            fields = viewModel.fields,
            errors = viewModel.errors,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.onSaveClick {
                        navigator.navigate(UserDetailsScreenDestination(it))
                    }
                }
            }
        )
    }
}



