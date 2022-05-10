package com.githubhub.ui.user_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.githubhub.ui.CircularImage
import com.githubhub.ui.PrimaryText
import com.githubhub.ui.composables.GitHubIcon
import com.githubhub.ui.extensions.getPlural
import com.githubhub.ui.model.UserDetail

@Composable
fun UserDetail(username: String) {

    val viewModel = hiltViewModel<UserDetailViewModel>()
    val state = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = 2) {
        viewModel.getGithubUserDetails(username)
    }
    Box(Modifier.fillMaxSize()) {

        when (state.value) {
            is UserDetailViewModel.UiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is UserDetailViewModel.UiState.Loaded -> {
                val userDetail = (state.value as UserDetailViewModel.UiState.Loaded).userDetail
                UserDetailScreen(user = userDetail)
            }

            is UserDetailViewModel.UiState.Error -> {
                val errorMsg = (state.value as UserDetailViewModel.UiState.Error).message
                PrimaryText(text = "Oops $errorMsg", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun UserDetailScreen(user: UserDetail) {

    Box(
        Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 48.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularImage(
                imageUrl = user.avatarUrl, modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(8.dp))

            PrimaryText(text = user.name)

            GitHubIcon(
                user.githubUrl, modifier = Modifier
                    .size(24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.SpaceAround) {

                PrimaryText(text = "${user.followers} follower".getPlural(user.followers), textSize = 10.sp)

                Spacer(modifier = Modifier.width(4.dp))

                PrimaryText(text = "|", textSize = 10.sp)

                Spacer(modifier = Modifier.width(4.dp))

                PrimaryText(text = "following ${user.following} user".getPlural(user.following), textSize = 10.sp)

                Spacer(modifier = Modifier.width(4.dp))

                PrimaryText(text = "|", textSize = 10.sp)

                Spacer(modifier = Modifier.width(4.dp))

                PrimaryText(text = "${user.repos} public repo".getPlural(user.repos), textSize = 10.sp)

            }
            Spacer(modifier = Modifier.height(8.dp))

            if (user.location.isEmpty().not()) {

                PrimaryText(text = "lives at ${user.location}", textSize = 10.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryText(text = user.bio, textSize = 12.sp)

        }
    }
}


