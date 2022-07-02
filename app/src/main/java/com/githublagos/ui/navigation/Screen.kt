package com.githublagos.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favourite : Screen("fav")
    object Detail : Screen("details/{username}") {
        fun createRoute(username: String) = "details/$username"
    }
}