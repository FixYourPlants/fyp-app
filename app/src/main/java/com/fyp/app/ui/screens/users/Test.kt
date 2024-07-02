package com.fyp.app.ui.screens.users

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.fyp.app.BuildConfig
import com.fyp.app.R
import com.fyp.app.data.api.PlantServiceImp
import com.fyp.app.data.model.db.Difficulty
import com.fyp.app.data.model.db.Plant
import com.fyp.app.data.model.db.User
import com.fyp.app.ui.components.BoxLongText
import com.fyp.app.ui.components.DetailBackground
import com.fyp.app.ui.components.buttons.DefaultButton
import com.fyp.app.ui.screens.destinations.PlantDetailsScreenDestination
import com.fyp.app.ui.screens.destinations.UserEditScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
@Destination
fun NewUserDetailsScreen(
    user: User
) {
    val favouritePlants = remember { mutableStateOf(listOf<Plant>()) }

    LaunchedEffect(Unit) {
        val plants = user.favouritePlants.mapNotNull { plantId ->
            withContext(Dispatchers.IO) {
                try {
                    PlantServiceImp.getInstance().getPlantById(plantId)
                } catch (e: Exception) {
                    Log.e("UserDetailsScreen", "Error loading plant", e)
                    null
                }
            }
        }
        favouritePlants.value = plants
    }

    DetailBackground {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .border(
                    width = 3.0.dp,
                    color = Color(59, 170, 0, 255),
                    shape = RoundedCornerShape(8.dp),
                )
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(Color(226, 237, 169, 255), shape = RoundedCornerShape(8.dp))
                        .zIndex(0f)
                ){
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Gray)
                                .padding(8.dp),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                AsyncImage(
                                    model = BuildConfig.BACKEND_URL + user.imageUrl,
                                    placeholder = painterResource(id = R.drawable.default_user),
                                    error = painterResource(id = R.drawable.default_user),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clip(shape = CircleShape)
                                        .border(
                                            width = 2.0.dp,
                                            color = Color(139, 195, 74, 255),
                                            shape = CircleShape,
                                        ),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = user.username,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 24.sp,
                                        color = Color.Black
                                    )
                                    if (user.firstName != null) {
                                        Text(
                                            text = "${user.firstName} ${user.lastName}",
                                            fontStyle = FontStyle.Italic,
                                            fontSize = 18.sp,
                                            color = Color.Black
                                        )
                                    }
                                    if (user.email != null) {
                                        Text(
                                            text = user.email,
                                            fontStyle = FontStyle.Italic,
                                            fontSize = 12.sp,
                                            color = Color.Black
                                        )
                                    }
                                }
                            }
                        }
                        // Transformar esto en LazyColumn
                        Column(modifier = Modifier.padding(8.dp)) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Sobre mí...",
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            HorizontalDivider(
                                thickness = 2.dp,
                                color = Color(59, 170, 0, 255),
                                modifier = Modifier.padding(4.dp)
                            )
                            BoxLongText(text = user.aboutMe)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Log.d("UserDetailsScreen", "Favourite plants: ${favouritePlants.value}")
                        NewUserFavoritePlantsSection(plants = favouritePlants.value)
                        Box(
                            modifier= Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            contentAlignment = Alignment.Center){
                            DefaultButton(
                                onClick = { }, // navigator.navigate(UserEditScreenDestination(user))
                                text = "Edit User"
                            )
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NewUserFavoritePlantsSection(plants: List<Plant>) {
    Spacer(modifier = Modifier.height(16.dp))
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(Color(0xFF2E5805), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(
            text = "Mis plantas:",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
        FlowRow(modifier = Modifier.fillMaxWidth()) {
            plants.forEach { plant ->
                Box(
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .clickable { } // navigator.navigate(PlantDetailsScreenDestination(plant))
                        .background(Color(0xFF7CFC00), RoundedCornerShape(8.dp))
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = plant.name,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewUserdetailsPreview(){
    val plantfav1 = Plant(
        id = "fegr",
        name = "Favorite plant 1",
        scientificName = "wcniccc",
        imageUrl = "https://preview.redd.it/peashooter-the-smoker-v0-8h7voprli7na1.png?width=640&crop=smart&auto=webp&s=b2653f87e40f0410ecf06eb2a3fe97884b0ed7b7",
        treatment = "efergrgr",
        description = "ergrgttrh",
        sicknesses = mutableListOf(),
        difficulty = Difficulty.EASY,
        characteristics = mutableListOf()
    )

    val user = User(
        id = "efef",
        email = "micorreo@gmail.com",
        aboutMe = "\n" +
                "Claro, aquí tienes un texto largo para la sección \"Sobre mí\" en un perfil de usuario:\n" +
                "\n" +
                "Soy un entusiasta de la tecnología y la innovación, apasionado por el desarrollo de soluciones creativas que mejoren la vida de las personas. Con una formación en ingeniería de software y una amplia experiencia en el campo de la inteligencia artificial, me especializo en diseñar y desarrollar aplicaciones que integran lo último en aprendizaje automático y procesamiento de datos.\n" +
                "\n" +
                "Desde joven, siempre he estado fascinado por el funcionamiento de las máquinas y cómo la tecnología puede transformar el mundo. Esto me llevó a estudiar ingeniería informática, donde descubrí mi amor por la programación y el diseño de sistemas inteligentes. Durante mi carrera, he tenido la oportunidad de trabajar en una variedad de proyectos, desde aplicaciones móviles hasta sistemas de recomendación y análisis de grandes volúmenes de datos.\n" +
                "\n" +
                "Además de mi experiencia profesional, también soy un ávido aprendiz. Me encanta mantenerme al día con las últimas tendencias y avances en el campo de la tecnología. Participo regularmente en conferencias y seminarios, y estoy constantemente buscando nuevas formas de mejorar mis habilidades y conocimientos. Actualmente, estoy explorando el mundo de la realidad aumentada y virtual, y cómo estas tecnologías emergentes pueden integrarse en soluciones prácticas y de entretenimiento.",
        lastName = "Apellido",
        firstName = "Nombre",
        password = "dceveve",
        username = "usertest1",
        imageUrl = "https://i1.sndcdn.com/artworks-000605223721-hyon7e-t500x500.jpg",
        favouritePlants = mutableListOf("7f73e7be04a24bd5b49484b86ca894e7", "8cf9abd841ee40b0a7d2df2eec4bc812","9b56a239c08e4c558b578ce08aa84b5e")
    )
    NewUserDetailsScreen(user = user)
}



