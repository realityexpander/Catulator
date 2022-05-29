package com.realityexpander.catulator

sealed class CatulatorAction {
    data class Number(val number: Int): CatulatorAction()
    object Decimal: CatulatorAction()
    object Clear: CatulatorAction()
    object Delete: CatulatorAction()
    object Calculate: CatulatorAction()
    object ChangeSign: CatulatorAction()
    data class Operation(val operation: CatulatorOperation): CatulatorAction()
}
