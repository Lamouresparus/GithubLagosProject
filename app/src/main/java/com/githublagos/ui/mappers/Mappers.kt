package com.githublagos.ui.mappers

import com.githublagos.domain.model.UserDetailDomain
import com.githublagos.domain.model.UserDomain
import com.githublagos.ui.model.User
import com.githublagos.ui.model.UserDetail

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

fun UserDetail.mapToDomain(): UserDetailDomain {
    return UserDetailDomain(
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


fun UserDetailDomain.mapToUser() = User(
    username = username,
    avatarUrl = avatarUrl,
    githubUrl = githubUrl
)