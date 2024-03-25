package com.fyp.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.fyp.app.ui.components.ListBox

@Composable
fun PlantListScreen() {
    val plants = remember { llamada_api() }
    Column {
        ListBox(content = plants, type="PLANTS")
    }
}
@Composable
fun IllnessListScreen() {
    val illness = remember { llamada_api() }
    Column {
        ListBox(content = illness, type="ILLNESS")
    }
}
@Composable
fun PlaguesListScreen() {
    val plagues = remember { llamada_api() }
    Column {
        ListBox(content = plagues, type="PLAGUES")
    }
}
fun llamada_api(): List<List<String>> {
    val planta1= listOf("La Langosta come hombres","image","Si tienes insecticida esta easy", "Mi nombre es Iñigo Montoya y tu mataste a mi padre. Prepárate a morir (En idioma de insectos)")
    val planta2= listOf("Chupame el orto","image","Difícil: no llego", "Soy Sagitario bby")
    val content= listOf(planta1,planta2)
    return content
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PlantListScreenPreview(){
    PlantListScreen()
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PlaguesListScreenPreview(){
    PlaguesListScreen()
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun IllnessListScreenPreview(){
    IllnessListScreen()
}
