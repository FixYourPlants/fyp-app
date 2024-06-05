package com.fyp.app.ui.screens.page

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fyp.app.data.api.PageServiceImp
import com.fyp.app.data.model.db.Page
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
@Destination
fun PageDetailsScreen(
    navigator: DestinationsNavigator,
    page: Page
) {
    val updatedTitle = remember { mutableStateOf(page.title) }
    val updatedContent = remember { mutableStateOf(page.content) }
    val canEdit = isToday(page.createdAt)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .padding(2.dp)
            .background(Color(0xFF000500))
            .padding(2.dp)
            .background(Color(0xFF4CAF50))
            .padding(16.dp)
    ) {
        item { PageDetailsHeader(page) }
        item { PageContentSection(page, updatedTitle, updatedContent, canEdit) }
        if (canEdit) {
            item { UpdatePageButton(page, updatedTitle.value, updatedContent.value) }
        } else {
            item { EditNotAllowedMessage() }
        }
    }
}

@Composable
fun PageDetailsHeader(page: Page) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(0.5f)) {
            Surface(
                modifier = Modifier
                    .requiredSize(84.dp)
                    .border(width = 1.dp, color = Color.Black)
            ) {
                AsyncImage(
                    model = page.imageUrl,
                    contentDescription = "Page Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .background(color = Color.White)
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(0.5f)) {
            Text(
                text = page.title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black
            )
            Text(
                text = "Fecha de creación: ${page.createdAt}",
                fontSize = 12.sp,
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.Black
            )
        }
    }
}

@Composable
fun PageContentSection(page: Page, updatedTitle: MutableState<String>, updatedContent: MutableState<String>, canEdit: Boolean) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Título",
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
    OutlinedTextField(
        value = updatedTitle.value,
        onValueChange = { updatedTitle.value = it },
        modifier = Modifier.fillMaxWidth(),
        enabled = canEdit
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Contenido",
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
    OutlinedTextField(
        value = updatedContent.value,
        onValueChange = { updatedContent.value = it },
        modifier = Modifier.fillMaxWidth(),
        maxLines = 10,
        enabled = canEdit
    )
}

@Composable
fun UpdatePageButton(page: Page, updatedTitle: String, updatedContent: String) {
    val context = LocalContext.current
    Button(
        onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val updatedPage = page.copy(title = updatedTitle, content = updatedContent)
                    PageServiceImp.getInstance().updatePage(updatedPage.id, updatedPage)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Página actualizada", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Error al actualizar la página", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        },
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
    ) {
        Text(text = "Actualizar Página")
    }
}

@Composable
fun EditNotAllowedMessage() {
    Text(
        text = "Solo puedes editar la página el día de su creación.",
        color = Color.Red,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 16.dp)
    )
}

fun isToday(dateString: String): Boolean {
    return try {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = format.parse(dateString)
        val today = format.parse(format.format(Date()))
        date == today
    } catch (e: Exception) {
        false
    }
}
