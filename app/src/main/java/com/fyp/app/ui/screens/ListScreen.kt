package com.fyp.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.fyp.app.data.repository.Repository
import com.fyp.app.ui.components.Header
import com.fyp.app.ui.components.ListBoxAlerts
import com.fyp.app.ui.components.ListBoxIllness
import com.fyp.app.ui.components.ListBoxPlants

@Composable
fun PlantListScreen() {
    val plants = remember { Repository.plants }
    Column {
        Header()
        ListBoxPlants(content = plants)
    }
}
@Composable
fun IllnessListScreen() {
    val illness = remember { Repository.illness }
    Column {
        Header()
        ListBoxIllness(content = illness)
    }
}
@Composable
fun PlaguesListScreen() {
    val plagues = remember { getPlaguesList() }
    Column {
        Header()
        ListBoxAlerts(content = plagues)
    }
}
fun getPlaguesList(): MutableList<MutableList<String>> {
    val content = mutableListOf(mutableListOf("Alerta1","image1","Descripción 1"),mutableListOf("Alerta2","image2","Descripción 2"),mutableListOf("Alerta2","image2","Descripción 2"))
    return content
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PlantListScreenPreview(){
    Repository.populate()
    PlantListScreen()
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PlaguesListScreenPreview(){
    Repository.populate()
    PlaguesListScreen()
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun IllnessListScreenPreview(){
    Repository.populate()
    IllnessListScreen()
}
