package k90ms.compose.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNav(
    val name: String,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
)