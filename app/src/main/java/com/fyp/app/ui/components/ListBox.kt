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
import com.fyp.app.data.model.db.Sickness
import com.fyp.app.data.model.db.Plant

@Composable
fun ListBoxPlants(content: MutableList<Plant>){
    LazyColumn(modifier = Modifier
        .padding(10.dp)
        .border(width = 2.dp, color = Color.Black)
    ) {
        items(content){ plant->
            ContainerPlants(title = plant.name, image = plant.imageUrl, difficulty = plant.difficulty, description = plant.description)
        }
    }
}

@Composable
fun ListBoxIllness(content:List<Sickness>){
    LazyColumn(modifier = Modifier
        .padding(10.dp)
        .border(width = 2.dp, color = Color.Black)
    ) {
        items(content){ illness->
            ContainerIllness(title = illness.name, image = illness.imageUrl, description = illness.description)
        }
    }
}
@Composable
fun ListBoxAlerts(content:List<List<String>>){
    LazyColumn(modifier = Modifier
        .padding(10.dp)
        .border(width = 2.dp, color = Color.Black)
    ) {
        items(content){ list->
            ContainerPlague(title = list[0], image = list[1], description = list[2])
        }
    }
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ListBoxPreview(){
    ///
}