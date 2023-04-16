package k90ms.compose.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import k90ms.compose.navigation.BottomNav

@Composable
fun BottomNavigationBar(
    items: List<BottomNav>,
    currentScreen: String?,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNav) -> Unit
) {

    BottomNavigation(
        modifier = modifier,
        elevation = 5.dp
    ) {
        items.forEach {
            val selected = it.route == currentScreen

            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(it) },
                icon = {

                }
            )
        }
    }

}