package com.satispay.pokedex.ui.items

import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.satispay.pokedex.navigation.routes.MainRoutes
import com.satispay.pokedex.utils.Globals.getCurrentRoute

@Composable
fun PokedexBottomAppBar(
    navController: NavHostController,
    currentPage: Int,
    onPageSelected: (Int) -> Unit
) {
    val currentRoute: String? = getCurrentRoute(navController)
    var itemColor: Color = MaterialTheme.colorScheme.primary

    if (MainRoutes.entries.any { it.route == currentRoute }) {
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = itemColor,
            actions = {
                MainRoutes.entries.forEachIndexed { index, screen ->

                    val isSelected = index == currentPage

                    val iconRes = if (isSelected) {
                        screen.selectedIconRes
                    } else {
                        screen.unselectedIconRes
                    }

                    itemColor = if (isSelected)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)

                    IconButton(
                        modifier = Modifier.weight(weight = 1f),
                        onClick = {
                            // if (currentPage != index) {
                            onPageSelected(index)
                            navController.navigate(route = screen.route) {
                                popUpTo(id = navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                            // }
                        }
                    ) {
                        val iconPainter = painterResource(id = iconRes)
                        Icon(
                            modifier = Modifier.size(size = 36.dp),
                            painter = iconPainter,
                            contentDescription = screen.label,
                            tint = itemColor
                        )
                    }
                }
            }
        )
    }
}