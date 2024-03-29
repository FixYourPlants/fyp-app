package com.fyp.app.data.model

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