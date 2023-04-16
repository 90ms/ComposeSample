package k90ms.compose.navigation.graph

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import k90ms.compose.design.base.BaseViewModel
import k90ms.compose.main_list.MainListRoute
import k90ms.compose.navigation.Destinations
import k90ms.compose.navigation.ext.navigate

fun NavGraphBuilder.home(
    navController: NavController,
    onProvideBaseViewModel: (baseViewModel: BaseViewModel) -> Unit,
) {
    composable(Destinations.HomeScreen.route) {
        MainListRoute(
            onNavigateToDetailScreen = {
                navController.navigate(
                    route = Destinations.DetailScreen().route,
                    args = bundleOf(Destinations.DetailScreen().data to it)
                )
            },
            onProvideBaseViewModel = {
                onProvideBaseViewModel(it)
            }
        )
    }
}