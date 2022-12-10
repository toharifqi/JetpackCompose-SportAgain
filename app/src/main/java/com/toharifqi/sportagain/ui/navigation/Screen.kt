package com.toharifqi.sportagain.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object About : Screen("about")
    object SportDetail : Screen("home/{sportId}") {
        fun createRoute(sportId: String) = "home/$sportId"
    }
}
