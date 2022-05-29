package com.realityexpander.catulator

sealed class CatulatorOperation(val symbol: String) {
    object Add : CatulatorOperation("+")
    object Subtract : CatulatorOperation("-")
    object Multiply : CatulatorOperation("×")
    object Divide : CatulatorOperation("÷")
    object None : CatulatorOperation("")
    object Result: CatulatorOperation("")
}
