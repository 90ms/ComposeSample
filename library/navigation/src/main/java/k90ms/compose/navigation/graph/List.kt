package k90ms.compose.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import k90ms.compose.design.base.BaseViewModel
import k90ms.compose.navigation.Destinations

fun NavGraphBuilder.list(
    navController: NavController,
    onProvideBaseViewModel: (baseViewModel: BaseViewModel) -> Unit,
) {
    composable(Destinations.ListScreen.route) {

    }
}