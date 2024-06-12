package com.fyp.app.ui.screens.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.fyp.app.data.model.db.Diary
import com.fyp.app.ui.screens.FormScreenContent
import com.fyp.app.ui.screens.destinations.CameraScreenDestination
import com.fyp.app.ui.screens.destinations.PageListScreenDestination
import com.fyp.app.viewmodel.PageCreateViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Composable
@Destination
fun PageCreateScreen(
    navigator: DestinationsNavigator,
    diary: Diary,
) {
    val viewModel = remember { PageCreateViewModel(diary) }
    val updatedImages by viewModel.cameraViewModel.bitmaps.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    // TODO: Gestionar las imagenes.
    // viewModel.cameraViewModel.clearBitmaps()
    // viewModel.cameraViewModel.clearSelectedBitmap()

    FormScreenContent(
        title = "Crear página en ${diary.title.lowercase()} el ${viewModel.formattedDate}",
        updatedImages = updatedImages,
        onAddImageClick = { navigator.navigate(CameraScreenDestination) },
        fields = viewModel.fields,
        errors = viewModel.errors,
        onSaveClick = {
            coroutineScope.launch {
                viewModel.onSaveClick {
                    navigator.navigate(PageListScreenDestination(diary))
                }
            }
        }
    )
}







