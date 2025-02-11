package com.satispay.pokedex

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.satispay.pokedex.data.datasource.local.DataStoreRepository
import com.satispay.pokedex.navigation.navgraph.RootNavGraph
import com.satispay.pokedex.presentation.viewmodel.MainViewModel
import com.satispay.pokedex.ui.items.PokedexModalNavigationDrawer
import com.satispay.pokedex.ui.theme.PokedexTheme
import com.satispay.pokedex.utils.Globals.getCurrentRoute
import com.satispay.pokedex.utils.Globals.mainRoutes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (mainViewModel.firstAccess.value) {
            setTheme(R.style.Theme_Pokedex)
        } else {
            mainViewModel.setFirstAccess(firstAccess = true)
            mainViewModel.setSplashOpened(state = true)
            installSplashScreen().setKeepOnScreenCondition {
                mainViewModel.keepSplashOpened.value
            }
        }

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)
        )

        setContent {
            val context = LocalContext.current
            val scope = rememberCoroutineScope()
            val repository = DataStoreRepository(context)

            val darkThemeFlow: Flow<Boolean> = repository.isDarkTheme()
            val pokedexColorFlow: Flow<Boolean> = repository.isDynamicColor()
            var darkTheme: Boolean = remember { darkThemeFlow }.collectAsState(initial = false).value
            var pokedexColor: Boolean = remember { pokedexColorFlow }.collectAsState(initial = false).value

            var currentPage: Int by remember { mutableIntStateOf(0) }
            val pagerState: PagerState = rememberPagerState(pageCount = {2})

            PokedexTheme(darkTheme = darkTheme, dynamicColor = !pokedexColor) {

                val navController: NavHostController = rememberNavController()
                val currentRoute = getCurrentRoute(navController = navController)

                if (currentRoute !in mainRoutes) {
                    RootNavGraph(
                        navController = navController,
                        mainViewModel = mainViewModel
                    )
                } else {
                    PokedexModalNavigationDrawer(
                        darkTheme = darkTheme,
                        dynamicColor = pokedexColor,
                        onThemeUpdated = {
                            darkTheme = !darkTheme
                            scope.launch { repository.setDarkTheme(theme = darkTheme) }
                        },
                        onColorUpdated = {
                            pokedexColor = !pokedexColor
                            scope.launch { repository.setDynamicColor(color = pokedexColor) }
                        },
                        mainViewModel = mainViewModel,
                        navController = navController,
                        currentPage = currentPage,
                        pagerState = pagerState,
                        onPageSelected = { page ->
                            currentPage = page
                        }
                    )
                }
            }
        }
    }
}