package com.githublagos.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.githublagos.R
import com.githublagos.ui.CircularImage
import com.githublagos.ui.PrimaryText
import com.githublagos.ui.model.User

@Composable
fun UserCard(user: User, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .testTag(
                stringResource(R.string.test_tag_user_card)
            )
            .clickable { onClick(user.username) }
    ) {
        Row(Modifier.align(Alignment.CenterStart)) {
            CircularImage(
                imageUrl = user.avatarUrl, Modifier
                    .size(40.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(12.dp))
            PrimaryText(text = user.username, modifier = Modifier.align(Alignment.CenterVertically))
        }

        GitHubIcon(
            user.githubUrl, Modifier
                .size(24.dp)
                .align(Alignment.CenterEnd)
        )

        Divider(
            Modifier
                .padding(top = 64.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}