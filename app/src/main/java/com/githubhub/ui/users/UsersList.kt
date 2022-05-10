package com.githubhub.ui.users

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.githubhub.ui.PrimaryText
import com.githubhub.ui.composables.UserCard
import com.githubhub.ui.navigation.Screen

@Composable
fun UsersList(controller: NavHostController) {
    val viewModel = hiltViewModel<UsersViewModel>()
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = 1){
        viewModel.getGithubUsers()
    }

    Box(Modifier.fillMaxSize()) {

        when (val state = uiState.value) {
            is UsersViewModel.UiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is UsersViewModel.UiState.Loaded -> {
                val users = state.users
                if (!users.isNullOrEmpty()) {
                    LazyColumn {
                        items(users) {
                            UserCard(user = it){controller.navigate(Screen.Detail.createRoute(it.username))}
                        }
                    }
                } else {
                    PrimaryText(text = "Oops couldn't get Github users at this time", modifier = Modifier.align(Alignment.Center))
                }
            }

            is UsersViewModel.UiState.Error -> {
                val errorMsg = state.message
                PrimaryText(text = "Oops $errorMsg", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}