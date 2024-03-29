package com.fyp.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.app.R
import com.fyp.app.ui.components.BoxTag
import com.fyp.app.ui.screens.plants.Opinion
import com.fyp.app.ui.screens.plants.Plant
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

data class User(
    val name: String,
    val about_me: String,
    val imageUrl: Int,
    val my_plants: List<Plant>,
    val email: String
)

val userDetails = User(
    name = "Pepe el Papas",
    about_me = "Zoy un cutivaó de papa profeziona de la provinzia de Huerva",
    imageUrl = R.drawable.user,
    my_plants = listOf(
        Plant(
            name = "Plant 1",
            description = "Plant description",
            imageUrl = R.drawable.plant,
            characteristics = listOf(
                "Plant characteristics"
            ),
            dificulty = "Plant dificulty",
            treatments = listOf(
                "Plant treatment"
            ),
            sickness = listOf(
                "Plant sickness"
            ),
            opinions = listOf(
                Opinion(
                    title = "Opinion title",
                    description = "Opinion description",
                    userName = "Opinion user"
                )
            ),
            scienceName = "Plant science name"
        ),
        Plant(
            name = "Plant 2",
            description = "Plant description",
            imageUrl = R.drawable.plant,
            characteristics = listOf(
                "Plant characteristics"
            ),
            dificulty = "Plant dificulty",
            treatments = listOf(
                "Plant treatment"
            ),
            sickness = listOf(
                "Plant sickness"
            ),
            opinions = listOf(
                Opinion(
                    title = "Opinion title",
                    description = "Opinion description",
                    userName = "Opinion user"
                )
            ),
            scienceName = "Plant science name"
        ),
        Plant(
            name = "Plant 1",
            description = "Plant description",
            imageUrl = R.drawable.plant,
            characteristics = listOf(
                "Plant characteristics"
            ),
            dificulty = "Plant dificulty",
            treatments = listOf(
                "Plant treatment"
            ),
            sickness = listOf(
                "Plant sickness"
            ),
            opinions = listOf(
                Opinion(
                    title = "Opinion title",
                    description = "Opinion description",
                    userName = "Opinion user"
                )
            ),
            scienceName = "Plant science name"
        )
    ),
    email = "ejemplo@gmail.com"

)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun UserDetailsScreen(
    navigator: DestinationsNavigator,
    user: User
) {
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
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        // Fondo: Imagen de la planta
                        Image(
                            painter = painterResource(id = user.imageUrl),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(shape = MaterialTheme.shapes.medium),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                ) {
                    Text(
                        text = user.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                    Text(
                        text = user.email,
                        fontStyle = FontStyle.Italic,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = Color.Black
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Sobre mí...",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(2.dp),
                color = Color(0xFFA5FFA9)
            ) {
                Column {

                        Text(
                            text = user.about_me,
                            modifier = Modifier.padding(vertical = 4.dp),
                            color = Color.Black
                        )

                }
            }
        }


        item {
            Spacer(modifier = Modifier.height(16.dp))
            BoxTag(name = "Mis plantas:", values = user.my_plants.map { it.name })
        }
    }
}