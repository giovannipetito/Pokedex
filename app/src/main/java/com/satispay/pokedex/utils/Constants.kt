package com.satispay.pokedex.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

object Constants {

    val TOP_BAR_HEIGHT = 64.dp
    val STATUS_BAR_HEIGHT = 24.dp
    val NAVIGATION_BAR_HEIGHT = 48.dp
    val HUB_TOP_BAR_PORTRAIT_HEIGHT = 95.dp
    val HUB_TOP_BAR_LANDSCAPE_HEIGHT = 126.dp

    @Composable
    fun getNumbers(): List<Int> {
        val numbers: List<Int> = remember {
            mutableStateListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
        }
        return numbers
    }

    val icons: List<ImageVector> = listOf(
        Icons.Default.Home,
        Icons.Default.Face,
        Icons.Default.Email,
        Icons.Default.Call,
        Icons.Default.Check,
        Icons.Default.Edit
    )
}