package com.satispay.pokedex.ui.items

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.satispay.pokedex.presentation.screen.main.HomeScreen
import com.satispay.pokedex.presentation.screen.main.ProfileScreen
import com.satispay.pokedex.presentation.screen.main.SettingsScreen
import com.satispay.pokedex.presentation.viewmodel.MainViewModel
import com.satispay.pokedex.utils.Globals.getCurrentRoute
import com.satispay.pokedex.utils.Globals.getMainBackgroundColors
import com.satispay.pokedex.utils.Globals.mainRoutes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun PokedexModalNavigationDrawer(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    onThemeUpdated: () -> Unit,
    onColorUpdated: () -> Unit,
    mainViewModel: MainViewModel,
    navController: NavHostController,
    currentPage: Int,
    pagerState: PagerState,
    onPageSelected: (Int) -> Unit,
) {
    val currentRoute = getCurrentRoute(navController = navController)

    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val drawerScope: CoroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect { page ->
                onPageSelected(page)
            }
    }

    LaunchedEffect(key1 = currentPage) {
        pagerState.animateScrollToPage(currentPage)
    }

    BackHandler(enabled = currentPage != 0) {
        onPageSelected(0)
    }

    if (drawerState.isOpen) {
        BackHandler {
            drawerScope.launch {
                drawerState.close()
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            PokedexModalDrawerSheet(
                drawerState = drawerState,
                drawerScope = drawerScope,
                darkTheme = darkTheme,
                dynamicColor = dynamicColor,
                onThemeUpdated = onThemeUpdated,
                onColorUpdated = onColorUpdated
            )
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            floatingActionButton = {
                if (currentRoute in mainRoutes) {
                    ExtendedFloatingActionButton(
                        text = {
                            var text: String
                            drawerState.apply {
                                text = if (isClosed) "Open"
                                else "Close"
                            }
                            Text(text = text)
                        },
                        icon = { Icon(Icons.Filled.Menu, contentDescription = "") },
                        onClick = {
                            drawerScope.launch {
                                drawerState.apply {
                                    if (isClosed) open()
                                    else close()
                                }
                            }
                        }
                    )
                }
            },
            bottomBar = {
                PokedexBottomAppBar(
                    navController = navController,
                    currentPage = currentPage,
                    onPageSelected = onPageSelected
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier
                .fillMaxSize()
                .background(brush = getMainBackgroundColors())
                .padding(bottom = paddingValues.calculateBottomPadding()),
                contentAlignment = Alignment.Center
            ) {
                HorizontalPager(
                    state = pagerState
                ) { index ->
                    when (index) {
                        0 -> HomeScreen(navController = navController, mainViewModel = mainViewModel)
                        1 -> ProfileScreen(navController = navController)
                        2 -> SettingsScreen(navController = navController)
                    }
                }
            }
        }
    }
}