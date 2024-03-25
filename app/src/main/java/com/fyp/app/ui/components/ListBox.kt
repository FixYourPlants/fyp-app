package com.fyp.app.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ListBox(type:String, content:List<List<String>>){
    when (type) {
        "PLANTS" -> {
            LazyColumn(modifier = Modifier
                .padding(10.dp)
                .border(width = 2.dp, color = Color.Black)
            ) {
                items(content){ list->
                    ContainerPlants(title = list[0], image = list[1], difficulty = list[2], description = list[3])
                }
            }
        }
        "ILLNESS" -> {
            LazyColumn(modifier = Modifier
                .padding(10.dp)
                .border(width = 2.dp, color = Color.Black)
            ) {
                items(content){ list->
                    ContainerIllness(title = list[0], image = list[1], description = list[2])
                }
            }
        }
        else -> {
            LazyColumn(modifier = Modifier
                .padding(10.dp)
                .border(width = 2.dp, color = Color.Black)
            ) {
                items(content){ list->
                    ContainerPlague(title = list[0], image = list[1], description = list[2])
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ListBoxPreview(){
    val planta1= listOf("La Langosta come hombres","image","Si tienes insecticida esta easy", "Mi nombre es Iñigo Montoya y tu mataste a mi padre. Prepárate a morir (En idioma de insectos)")
    val planta2= listOf("Chupame el orto","image","Difícil: no llego", "Soy Sagitario bby")
    val content= listOf(planta1,planta2)
    ListBox("PLANTS",content)
}