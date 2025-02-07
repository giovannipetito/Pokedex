package com.satispay.pokedex.ui.items

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties

@Composable
fun InfoDialog(
    topics: List<String>,
    showDialog: MutableState<Boolean>,
    onConfirmation: () -> Unit
) {
    if (showDialog.value) {
        AlertDialog(
            icon = { Icon(imageVector = Icons.Filled.Info, contentDescription = "Info Icon") },
            title = { Text(text = "Topics") },
            text = {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        topics.forEach { topic ->
                            Text(text = topic)
                        }
                    }
                }
            },
            onDismissRequest = {},
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmation()
                    }
                ) {
                    Text(text = "Close")
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }
}