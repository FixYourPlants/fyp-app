package com.fyp.app.utils

import com.fyp.app.data.model.db.Plant
import com.fyp.app.data.model.db.Sickness
import java.text.Normalizer

fun removeAccents(input: String): String {
    val normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
    return normalized.replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
}

fun filterPlants(searchText:String, plants:MutableList<Plant>):MutableList<Plant>{

    val normalizedSearchText = removeAccents(searchText.lowercase())
    var filteredlist: List<Plant> = plants.filter { it -> removeAccents(it.name.lowercase()).contains(normalizedSearchText.lowercase()) }
    return filteredlist.toMutableList()
}

fun filterSickness(searchText:String, sickness:MutableList<Sickness>): MutableList<Sickness> {
    val normalizedSearchText = removeAccents(searchText.lowercase())
    var filteredlist: List<Sickness> = sickness.filter { it -> removeAccents(it.name.lowercase()).contains(normalizedSearchText.lowercase()) }
    return filteredlist.toMutableList()
}