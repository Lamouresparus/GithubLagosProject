package com.githublagos.ui.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.githublagos.R
import com.githublagos.ui.PrimaryText
import com.githublagos.ui.composables.UserCard
import com.githublagos.ui.mappers.toUiModel
import com.githublagos.ui.navigation.Screen

@Composable
fun UsersList(controller: NavHostController) {
    val viewModel = hiltViewModel<UsersViewModel>()
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = 1) {
        viewModel.getGithubUsers()
    }

    UserListScreen(uiState.value, { controller.navigate(Screen.Detail.createRoute(it)) }, {controller.navigate(Screen.Favourite.route)})
}

@Composable
fun UserListScreen(uiState: UsersViewModel.UiState, onItemClick: (String) -> Unit, onFavouriteClick: () -> Unit) {
    Box(Modifier.fillMaxSize()) {

        when (uiState) {
            is UsersViewModel.UiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .testTag(
                            stringResource(R.string.test_tag_circular_progress)
                        )
                )
            }

            is UsersViewModel.UiState.Loaded -> {
                val users = uiState.users.collectAsLazyPagingItems()
                if (users.itemCount != 0) {
                    LazyColumn {
                        items(users.itemCount) {
                            users[it]?.let { user ->
                                UserCard(user = user.toUiModel(), onItemClick)
                            }
                        }
                    }
                }
            }

            is UsersViewModel.UiState.Error -> {
                val errorMsg = uiState.message
                PrimaryText(
                    text = "Oops $errorMsg", modifier = Modifier
                        .align(Alignment.Center)
                        .testTag(
                            stringResource(R.string.error_test_tag)
                        )
                )
            }
        }

        Box(modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(end = 24.dp, bottom = 24.dp)) {

            Image(
                painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                contentDescription = stringResource(id = R.string.profile_image),
                modifier = Modifier
                    .size(62.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
                    .padding(12.dp)
                    .clickable { onFavouriteClick() }

            )
        }
    }
}

