package com.satispay.pokedex.presentation.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.satispay.pokedex.R
import com.satispay.pokedex.ui.items.PokedexTopAppBar
import com.satispay.pokedex.ui.items.InfoDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(
    navController: NavController,
    title: String = stringResource(id = R.string.app_name),
    topics: List<String> = emptyList(),
    content: @Composable (PaddingValues) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val showDialog = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            PokedexTopAppBar(
                scrollBehavior = scrollBehavior,
                title = title,
                onInfoClick = {
                    showDialog.value = true
                },
                onNavigationClicked = {
                    if (navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED)
                        navController.popBackStack()
                }
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(top = paddingValues.calculateTopPadding()),
                contentAlignment = Alignment.Center
            ) {
                content(paddingValues)
                InfoDialog(
                    topics = topics,
                    showDialog = showDialog,
                    onConfirmation = {
                        showDialog.value = false
                    }
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BaseScreenPreview() {
    BaseScreen(
        navController = rememberNavController(),
        content = {}
    )
}