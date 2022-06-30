package com.githublagos.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.githublagos.ui.navigation.Screen
import com.githublagos.ui.user_detail.UserDetail
import com.githublagos.ui.users.UsersList

@Composable
fun App() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        // Navigation graph destinations
        composable(Screen.Home.route) {
            UsersList(navController)
        }

        composable(Screen.Detail.route){
            val userLogin = it.arguments?.getString("username") ?: ""
            UserDetail(userLogin)
        }
    }
}