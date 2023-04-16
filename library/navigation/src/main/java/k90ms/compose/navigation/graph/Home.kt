package k90ms.compose.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import k90ms.compose.navigation.Destinations

fun NavGraphBuilder.home(
    navController: NavController
) {
    composable(Destinations.HomeScreen.route) {

    }
}