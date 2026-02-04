package com.example.warcardgamecompose.ui.theme.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun StrokeText(
    text: String,
    fontSize: TextUnit,
    fontFamily: FontFamily,
    fillColor: Color,
    strokeColor: Color,
    strokeWidth: Dp = 1.dp
) {
    val offsets = listOf(
        Offset(-1f, -1f),
        Offset(0f, -1f),
        Offset(1f, -1f),
        Offset(-1f, 0f),
        Offset(1f, 0f),
        Offset(-1f, 1f),
        Offset(0f, 1f),
        Offset(1f, 1f)
    )

    Box(contentAlignment = Alignment.Center) {

        offsets.forEach { offset ->
            Text(
                text = text,
                fontSize = fontSize,
                fontFamily = fontFamily,
                color = strokeColor,
                modifier = Modifier.offset(
                    x = offset.x.dp * strokeWidth.value,
                    y = offset.y.dp * strokeWidth.value
                )
            )
        }

        Text(
            text = text,
            fontSize = fontSize,
            fontFamily = fontFamily,
            color = Color.Black,
            modifier = Modifier.offset(x = 3.dp, y = 3.dp),

            )

        Text(
            text = text,
            fontSize = fontSize,
            fontFamily = fontFamily,
            color = fillColor
        )


    }
}