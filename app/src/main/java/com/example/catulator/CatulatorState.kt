package com.example.catulator

data class CatulatorState(
    val number1: String = "",
    val number2: String = "",
    val operation: CatulatorOperation = CatulatorOperation.None,
)
