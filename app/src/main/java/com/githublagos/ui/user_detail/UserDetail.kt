package com.githublagos.ui.user_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.githublagos.R
import com.githublagos.ui.CircularImage
import com.githublagos.ui.PrimaryText
import com.githublagos.ui.composables.GitHubIcon
import com.githublagos.ui.extensions.getPlural
import com.githublagos.ui.model.UserDetail

@Composable
fun UserDetail(
    username: String, viewModel: UserDetailViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsState()
    val isFavourite = viewModel.isFavourite.collectAsState()


    LaunchedEffect(key1 = 2) {
        viewModel.getGithubUserDetails(username)
    }
    Box(Modifier.fillMaxSize()) {

        when (val state = uiState.value) {
            is UserDetailViewModel.UiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .testTag(
                            stringResource(R.string.test_tag_circular_progress)
                        )
                )
            }

            is UserDetailViewModel.UiState.Loaded -> {
                val userDetail = state.userDetail
                UserDetailScreen(user = userDetail, isFavourite) {
                    viewModel.addOrRemoveFromFavourite(
                        userDetail
                    )
                }
            }

            is UserDetailViewModel.UiState.Error -> {
                val errorMsg = state.message
                PrimaryText(text = "Oops $errorMsg", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun UserDetailScreen(user: UserDetail, isFavourite: State<Boolean>, onButtonClick: () -> Unit) {

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

            PrimaryText(text = if (user.name.isEmpty()) user.name else user.username)

            GitHubIcon(
                user.githubUrl, modifier = Modifier
                    .size(24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.SpaceAround) {

                PrimaryText(
                    text = "${user.followers} follower".getPlural(user.followers),
                    textSize = 12.sp
                )

                Spacer(modifier = Modifier.width(4.dp))

                PrimaryText(text = "|", textSize = 12.sp)

                Spacer(modifier = Modifier.width(4.dp))

                PrimaryText(
                    text = "following ${user.following} user".getPlural(user.following),
                    textSize = 12.sp
                )

                Spacer(modifier = Modifier.width(4.dp))

                PrimaryText(text = "|", textSize = 12.sp)

                Spacer(modifier = Modifier.width(4.dp))

                PrimaryText(
                    text = "${user.repos} public repo".getPlural(user.repos),
                    textSize = 12.sp
                )

            }
            Spacer(modifier = Modifier.height(8.dp))

            if (user.location.isEmpty().not()) {

                PrimaryText(text = "lives at ${user.location}", textSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryText(text = user.bio, textSize = 14.sp)

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = onButtonClick, modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.Black)
                    .clip(RoundedCornerShape(4.dp))
            ) {
                Text(
                    text = stringResource(id = if (isFavourite.value) R.string.remove_from_favourites else R.string.add_to_favourites),
                    color = Color.White
                )
            }

        }
    }
}


