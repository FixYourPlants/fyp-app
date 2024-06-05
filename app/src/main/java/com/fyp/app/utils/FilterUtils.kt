package com.fyp.app.utils

import com.fyp.app.data.model.db.Plant
import com.fyp.app.data.model.db.Sickness

fun filterPlants(searchText:String, plants:MutableList<Plant>):MutableList<Plant>{
    var filteredlist: List<Plant> = plants.filter { it -> it.name.lowercase().contains(searchText.lowercase()) }
    return filteredlist.toMutableList()
}

fun filterSickness(searchText:String, sickness:MutableList<Sickness>): MutableList<Sickness> {
    var filteredlist: List<Sickness> = sickness.filter { it -> it.name.lowercase().contains(searchText.lowercase()) }
    return filteredlist.toMutableList()
}