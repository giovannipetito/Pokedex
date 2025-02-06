package com.satispay.pokedex.ui.items

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.satispay.pokedex.R

@Composable
fun MainTextButton(onClick: () -> Unit, id: Int) {
    TextButton(onClick = { onClick() }) {
        Text(
            text = stringResource(id = id),
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainTextButtonPreview() {
    MainTextButton(onClick = {}, id = R.string.app_name)
}