package com.fyp.app.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream

class CameraViewModel : ViewModel() {

    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    private var selectedBitmap: Bitmap? = null
    val bitmaps: StateFlow<List<Bitmap>> get() = _bitmaps

    fun onTakePhoto(bitmap: Bitmap) {
        _bitmaps.value = _bitmaps.value + bitmap
    }

    fun onSelectBitmap(bitmap: Bitmap) {
        selectedBitmap = bitmap
    }

    fun getSelectedBitmap(): Bitmap? {
        return selectedBitmap
    }
}

object CameraViewModelImp : BaseViewModel<CameraViewModel>(CameraViewModel::class.java)

fun Bitmap.toMultipartBodyPart(name: String): MultipartBody.Part {
    val stream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, stream)
    val byteArray = stream.toByteArray()
    val requestBody = RequestBody.create("image/jpeg".toMediaTypeOrNull(), byteArray)
    return MultipartBody.Part.createFormData(name, "image.jpg", requestBody)
}