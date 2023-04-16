package k90ms.compose.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import k90ms.compose.navigation.Destinations

fun NavGraphBuilder.list(
    navController: NavController
) {
    composable(Destinations.ListScreen.route) {

    }
}