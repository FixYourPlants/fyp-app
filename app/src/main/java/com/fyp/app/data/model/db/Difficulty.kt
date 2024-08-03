package com.fyp.app.data.model.db

enum class Difficulty {
    EASY,
    MEDIUM,
    HIGH
}
fun obtainDifficulty(difficulty: Difficulty): String {
    return when (difficulty) {
        Difficulty.EASY -> "FÁCIL"
        Difficulty.MEDIUM -> "MEDIA"
        Difficulty.HIGH -> "ALTA"
    }
}