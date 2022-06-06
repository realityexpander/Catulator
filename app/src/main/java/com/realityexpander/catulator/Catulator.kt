package com.realityexpander.catulator

import android.text.TextUtils.replace
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.realityexpander.catulator.ui.theme.LightGray
import com.realityexpander.catulator.ui.theme.Orange

fun String.toCatZeros(): String {
    return replace("0", "🐈")
}

@Composable
fun Catulator(
    state: CatulatorState,
    buttonSpacing: Dp = 8.dp,
    modifier: Modifier = Modifier,
    onAction: (CatulatorAction) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Text(text = "😸 Catulator \uD83D\uDE40÷ \uD83D\uDE3B× \uD83D\uDE3F- \uD83D\uDE38+ \uD83D\uDC08=0",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            println(state)
            Text(
                text = (if (state.number1 == "") "🐈" else state.number1.toCatZeros()) +
                        state.operation.symbol +
                        state.number2.toCatZeros(),
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                fontWeight = FontWeight.Light,
                fontSize = 50.sp,
                color = Color.White,
                maxLines = 3
            )
            Row( // AC del ÷
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CatulatorButton(
                    symbol = "AC",
                    onClick = { onAction(CatulatorAction.Clear) },
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    color = Color.Black
                )
                CatulatorButton(
                    symbol = "⌫",
                    onClick = { onAction(CatulatorAction.Delete) },
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f)
                )
                CatulatorButton(
                    symbol = "+/-",
                    onClick = { onAction(CatulatorAction.ChangeSign) },
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f)
                )
                CatulatorButton(
                    symbol = "🙀", // ➗
                    onClick = { onAction(CatulatorAction.Operation(CatulatorOperation.Divide)) },
                    modifier = Modifier
                        .background(Color.Green)
                        .aspectRatio(1f)
                        .weight(1f)
                )
            }

            Row( // 7 8 9 x
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CatulatorButton(
                    symbol = "7",
                    onClick = { onAction(CatulatorAction.Number(7)) },
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f)
                )
                CatulatorButton(
                    symbol = "8",
                    onClick = { onAction(CatulatorAction.Number(8)) },
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f)
                )
                CatulatorButton(
                    symbol = "9",
                    onClick = { onAction(CatulatorAction.Number(9)) },
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f)
                )
                CatulatorButton(
                    symbol = "😻", // ️
                    onClick = { onAction(CatulatorAction.Operation(CatulatorOperation.Multiply)) },
                    modifier = Modifier
                        .background(Color.Green)
                        .aspectRatio(1f)
                        .weight(1f)
                )
            }

            Row( // 4 5 6 -
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CatulatorButton(
                    symbol = "4",
                    onClick = { onAction(CatulatorAction.Number(4)) },
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f)
                )
                CatulatorButton(
                    symbol = "5",
                    onClick = { onAction(CatulatorAction.Number(5)) },
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f)
                )
                CatulatorButton(
                    symbol = "6",
                    onClick = { onAction(CatulatorAction.Number(6)) },
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f)
                )
                CatulatorButton(
                    symbol = "😿", // ➖
                    onClick = { onAction(CatulatorAction.Operation(CatulatorOperation.Subtract)) },
                    modifier = Modifier
                        .background(Color.Green)
                        .aspectRatio(1f)
                        .weight(1f)
                )
            }

            Row( // 1 2 3 +
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CatulatorButton(
                    symbol = "1",
                    onClick = { onAction(CatulatorAction.Number(1)) },
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f)
                )
                CatulatorButton(
                    symbol = "2",
                    onClick = { onAction(CatulatorAction.Number(2)) },
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f)
                )
                CatulatorButton(
                    symbol = "3",
                    onClick = { onAction(CatulatorAction.Number(3)) },
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f)
                )
                CatulatorButton(
                    symbol = "😸", // ➕
                    onClick = { onAction(CatulatorAction.Operation(CatulatorOperation.Add)) },
                    modifier = Modifier
                        .background(Color.Green)
                        .aspectRatio(1f)
                        .weight(1f)
                )
            }

            Row( // 0 . =
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CatulatorButton(
                    symbol = "🐈",
                    onClick = { onAction(CatulatorAction.Number(0)) },
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f)
                )
                CatulatorButton(
                    symbol = ".",
                    onClick = { onAction(CatulatorAction.Decimal) },
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f)
                )
                CatulatorButton(
                    symbol = "=",
                    onClick = { onAction(CatulatorAction.Calculate) },
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(2f)
                        .weight(2f),
                    color = Color.Black
                )
            }
        }
    }
}