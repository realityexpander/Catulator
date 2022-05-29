package com.example.catulator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.round
import kotlin.math.truncate

class CatulatorViewModel : ViewModel() {
    var state by mutableStateOf(CatulatorState())
        private set

    var number1Val by mutableStateOf(0.0)
        private set
    var number2Val by mutableStateOf(0.0)
        private set

    fun onAction(action: CatulatorAction) {
        when (action) {
            is CatulatorAction.Number -> enterNumber(action.number)
            is CatulatorAction.Decimal -> enterDecimal()
            is CatulatorAction.Clear -> { state = CatulatorState(); number1Val = 0.0; number2Val = 0.0 }
            is CatulatorAction.Operation -> enterOperation(action.operation)
            is CatulatorAction.Calculate -> performCalculation()
            is CatulatorAction.Delete -> deleteDigit()
            is CatulatorAction.ChangeSign -> changeSign()
        }
    }

    private fun changeSign() {
        if (state.operation == CatulatorOperation.None || state.operation == CatulatorOperation.Result) {
            if (state.number1.isEmpty()) return

            if(containsDecimal(state.number1)) {
                number1Val = -number1Val
                state = state.copy(number1 = number1Val.toString())
                number1Val = state.number1.toDouble()
            } else {
                number1Val = -number1Val
                state = state.copy(number1 = number1Val.toInt().toString())
                number1Val = state.number1.toInt().toDouble()
            }
        } else {
            if (state.number2.isEmpty()) return

            if(containsDecimal(state.number2)) {
                number2Val = -number2Val
                state = state.copy(number2 = number2Val.toString())
                number2Val = state.number2.toDouble()
            } else {
                number2Val = -number2Val
                state = state.copy(number2 = number2Val.toInt().toString())
                number2Val = state.number2.toInt().toDouble()
            }
        }
    }

    private fun containsDecimal(number: String): Boolean {
        return number.contains(".")
    }

    private fun enterNumber(number: Int) {
        if (state.operation == CatulatorOperation.Result) {
            state = CatulatorState()
        }

        if (state.operation == CatulatorOperation.None && state.number2.isEmpty()) {
            state = state.copy(number1 = state.number1 + number)
            number1Val = state.number1.toDouble()
        } else {
            state = state.copy(number2 = state.number2 + number)
            number2Val = state.number2.toDouble()
        }
    }

    private fun deleteDigit() {
        if (state.operation == CatulatorOperation.Result) {
            state = CatulatorState()
        }

        if (state.operation == CatulatorOperation.None && state.number2.isEmpty()) {
            state = state.copy(number1 = state.number1.dropLast(1))
            number1Val = if (state.number1.isNotEmpty()) state.number1.toDouble()
            else 0.0
        } else {
            state = state.copy(number2 = state.number2.dropLast(1))
            number2Val = if (state.number2.isNotEmpty()) state.number2.toDouble()
            else 0.0
        }
    }

    private fun enterDecimal() {
        if (state.operation == CatulatorOperation.Result) {
            state = CatulatorState()
        }

        if (state.number2.isEmpty() && state.operation == CatulatorOperation.None) {
            if(containsDecimal(state.number1)) return

            if (state.number1.isEmpty()) {
                state = state.copy(number1 = "0.")
            } else {
                state = state.copy(number1 = state.number1 + ".")
            }
            number1Val = state.number1.toDouble()
        } else {
            if(containsDecimal(state.number2)) return

            if (state.number2.isEmpty()) {
                state = state.copy(number2 = "0.")
            } else {
                state = state.copy(number2 = state.number2 + ".")
            }
            number2Val = state.number2.toDouble()
        }
    }

    private fun enterOperation(operation: CatulatorOperation) {
        if (state.number2.isEmpty()) {
            state = state.copy(operation = operation)
        } else {
            state = state.copy(operation = operation)
            performCalculation(operation) // pass along next operation
        }
    }

    private fun performCalculation(nextOperation: CatulatorOperation = CatulatorOperation.Result) {
        val number1 = number1Val
        val number2 = number2Val
        val operation = state.operation
        val result = when (operation) {
            is CatulatorOperation.Add -> number1 + number2
            is CatulatorOperation.Subtract -> number1 - number2
            is CatulatorOperation.Multiply -> number1 * number2
            is CatulatorOperation.Divide -> number1 / number2
            is CatulatorOperation.None -> 0.0
            is CatulatorOperation.Result -> number1
        }

        // if result is a whole number, remove the decimal
        val resultRounded = result.round(10)
        val resultString = if (resultRounded.toInt() * 1_000_000_000 == (resultRounded * 1_000_000_000).toInt()) {
            resultRounded.toInt().toString()
        } else {
            resultRounded.toString()
        }

        state = state.copy(
            number1 = resultString, number2 = "", operation = nextOperation
        )
        number1Val = result
        number2Val = 0.0
    }

    private fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }
}