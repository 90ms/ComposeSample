package k90ms.compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import k90ms.compose.design.base.BaseViewModel
import k90ms.compose.navigation.Destinations
import k90ms.compose.navigation.graph.home
import k90ms.compose.navigation.graph.list
import k90ms.compose.navigation.graph.setting

@Composable
fun ComposeNavHost(
    navController: NavHostController,
    modifier: Modifier,
    onProvideBaseViewModel: (baseViewModel: BaseViewModel) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.HomeScreen.route,
        modifier = modifier,
    ) {
        home(navController, onProvideBaseViewModel)
        list(navController, onProvideBaseViewModel)
        setting(navController, onProvideBaseViewModel)
    }
}