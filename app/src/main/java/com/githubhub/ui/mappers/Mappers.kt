package com.githubhub.ui.mappers

import com.githubhub.domain.model.UserDetailDomain
import com.githubhub.domain.model.UserDomain
import com.githubhub.ui.model.User
import com.githubhub.ui.model.UserDetail

fun UserDetailDomain.toUiModel(): UserDetail {
    return UserDetail(
        username = username,
        avatarUrl = avatarUrl,
        githubUrl = githubUrl,
        name = name,
        location = location,
        followers = followers,
        following = following,
        repos = repos,
        bio = bio
    )
}

fun UserDomain.toUiModel(): User {
    return User(
        username = username,
        avatarUrl = avatarUrl,
        githubUrl = githubUrl
    )
}