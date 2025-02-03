package com.satispay.pokedex

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.satispay.pokedex.data.DataStoreRepository
import com.satispay.pokedex.navigation.navgraph.RootNavGraph
import com.satispay.pokedex.presentation.viewmodel.MainViewModel
import com.satispay.pokedex.ui.items.HubModalNavigationDrawer
import com.satispay.pokedex.ui.theme.PokedexTheme
import com.satispay.pokedex.utils.Globals.getCurrentRoute
import com.satispay.pokedex.utils.Globals.mainRoutes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
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
            val hubColorFlow: Flow<Boolean> = repository.isDynamicColor()
            var darkTheme: Boolean = remember { darkThemeFlow }.collectAsState(initial = false).value
            var hubColor: Boolean = remember { hubColorFlow }.collectAsState(initial = false).value

            var currentPage: Int by remember { mutableIntStateOf(0) }
            val pagerState: PagerState = rememberPagerState(pageCount = {3})

            PokedexTheme(darkTheme = darkTheme, dynamicColor = !hubColor) {

                val navController: NavHostController = rememberNavController()
                val currentRoute = getCurrentRoute(navController = navController)

                if (currentRoute !in mainRoutes) {
                    RootNavGraph(
                        navController = navController,
                        mainViewModel = mainViewModel
                    )
                } else {
                    HubModalNavigationDrawer(
                        darkTheme = darkTheme,
                        dynamicColor = hubColor,
                        onThemeUpdated = {
                            darkTheme = !darkTheme
                            scope.launch { repository.setDarkTheme(theme = darkTheme) }
                        },
                        onColorUpdated = {
                            hubColor = !hubColor
                            scope.launch { repository.setDynamicColor(color = hubColor) }
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

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun requestPermissions(vararg permissions: String) {
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            result.entries.forEach {
                Log.d("MainActivity", "${it.key} = ${it.value}")
            }
        }
        requestPermissionLauncher.launch(permissions.asList().toTypedArray())
    }
}