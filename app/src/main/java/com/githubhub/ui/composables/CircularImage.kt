package com.githubhub.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.githubhub.R

@Composable
fun CircularImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = rememberAsyncImagePainter(imageUrl),
        contentDescription = stringResource(id = R.string.profile_image),
        modifier = modifier.clip(CircleShape)

    )
}