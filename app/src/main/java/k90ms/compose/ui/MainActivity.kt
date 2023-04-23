package k90ms.compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import k90ms.compose.design.base.BaseRoute
import k90ms.compose.design.base.BaseViewModel
import k90ms.compose.design.theme.ComposeTheme
import k90ms.compose.navigation.BottomNav
import k90ms.compose.navigation.Destinations
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val bottomNavItems = listOf(
        BottomNav(
            name = "Home",
            route = Destinations.HomeScreen.route,
            icon = Icons.Default.Home
        ),
        BottomNav(
            name = "List",
            route = Destinations.ListScreen.route,
            icon = Icons.Default.List
        ),
        BottomNav(
            name = "Setting",
            route = Destinations.SettingScreen.route,
            icon = Icons.Default.Settings
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {

                var baseViewModel: BaseViewModel by remember {
                    mutableStateOf(BaseViewModel())
                }

                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()
                val currentScreenRoute = backStackEntry.value?.destination?.route
                val bottomNavVisible = bottomNavItems.any {
                    it.route == currentScreenRoute
                }

                Scaffold(
                    bottomBar = {
                        AnimatedVisibility(
                            visible = bottomNavVisible,
                            enter = slideInVertically { it },
                            exit = slideOutVertically { it }
                        ) {
                            BottomNavigationBar(
                                items = bottomNavItems,
                                currentScreen = currentScreenRoute,
                                onItemClick = {
                                    navController.navigate(it.route)
                                }
                            )
                        }
                    }
                ) { paddingValues ->
                    BaseRoute(baseViewModel = baseViewModel) {
                        ComposeNavHost(
                            navController = navController,
                            modifier = Modifier.padding(
                                bottom = paddingValues.calculateBottomPadding()
                            ),
                            onProvideBaseViewModel = { viewModel ->
                                baseViewModel = viewModel
                            }
                        )
                    }
                }
            }
        }
    }
}
