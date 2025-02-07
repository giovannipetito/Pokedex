package com.satispay.pokedex.ui.items

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun CustomTitle(color: Color) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = ParagraphStyle(textAlign = TextAlign.Center)
            ) {
                withStyle(
                    style = SpanStyle(
                        color = color,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    append("Pokemon")
                }
                withStyle(
                    style = SpanStyle(
                        color = color,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Box")
                }
            }
        }, modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = false)
@Composable
fun CustomTitlePreview() {
    CustomTitle(color = MaterialTheme.colorScheme.primary)
}