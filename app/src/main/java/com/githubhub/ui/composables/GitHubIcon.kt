package com.githubhub.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.githubhub.R

@Composable
fun GitHubIcon(
    uri: String,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current

    Icon(
        painter = painterResource(id = R.drawable.ic_github_svgrepo_com),
        contentDescription = stringResource(id = R.string.see_on_github),
        modifier = Modifier
            .testTag(
                stringResource(R.string.github_icon_test_tag)
            )
            .clickable {
                uriHandler.openUri(uri)
            }
            .then(modifier)
    )
}