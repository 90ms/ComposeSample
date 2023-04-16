package k90ms.compose.navigation

sealed class Destinations(val route: String) {
    object HomeScreen : Destinations("home_screen")
    object ListScreen : Destinations("list_screen")
    object SettingScreen : Destinations("setting_screen")
    data class DetailScreen(val data: String = "data"): Destinations("detail_screen")
}
