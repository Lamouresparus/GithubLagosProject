package com.githublagos.ui.favourite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.githublagos.R
import com.githublagos.ui.PrimaryText
import com.githublagos.ui.composables.UserCard
import com.githublagos.ui.navigation.Screen

@Composable
fun FavouriteList(controller: NavHostController) {
    val viewModel = hiltViewModel<FavouriteViewModel>()
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = 1) {
        viewModel.getGithubUsers()
    }

    FavListScreen(uiState.value) { controller.navigate(Screen.Detail.createRoute(it)) }
}

@Composable
fun FavListScreen(uiState: FavouriteViewModel.UiState, onItemClick: (String) -> Unit) {
    Box(Modifier.fillMaxSize()) {

        when (uiState) {
            is FavouriteViewModel.UiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .testTag(
                            stringResource(R.string.test_tag_circular_progress)
                        )
                )
            }

            is FavouriteViewModel.UiState.Loaded -> {
                val users = uiState.users
                if (users.isNotEmpty()) {
                    LazyColumn {
                        items(users.size) {
                            UserCard(user = users[it], onItemClick)
                        }
                    }
                } else {
                    PrimaryText(
                        text = "It Looks Lonely here :(", modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }

            is FavouriteViewModel.UiState.Error -> {
                val errorMsg = uiState.message
                PrimaryText(
                    text = "Oops $errorMsg", modifier = Modifier
                        .align(Alignment.Center)
                        .testTag(
                            stringResource(R.string.fav_error_test_tag)
                        )
                )
            }
        }
    }
}

