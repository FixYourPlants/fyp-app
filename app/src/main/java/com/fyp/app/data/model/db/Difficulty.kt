package com.fyp.app.data.model.db

enum class Difficulty {
    EASY,
    MEDIUM,
    HIGH
}
fun obtainDifficulty(dificulty: Difficulty): String {
    return when (dificulty) {
        Difficulty.EASY -> "FÁCIL"
        Difficulty.MEDIUM -> "MEDIA"
        Difficulty.HIGH -> "ALTA"
    }
}