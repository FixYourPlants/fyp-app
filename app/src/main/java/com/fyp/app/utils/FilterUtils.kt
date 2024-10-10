package com.fyp.app.utils

import com.fyp.app.data.model.db.History
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

fun filterHistory(query: String, histories: List<History>): List<History> {
    val normalizedSearchText = removeAccents(query.lowercase())
    return histories.filter { history ->
        val plantName = history.plant?.name?.let { removeAccents(it.lowercase()) } ?: ""
        val sicknessName = history.sickness?.name?.let { removeAccents(it.lowercase()) } ?: ""
        plantName.contains(normalizedSearchText) || sicknessName.contains(normalizedSearchText)
    }
}