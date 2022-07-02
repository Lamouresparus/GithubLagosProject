package com.githublagos.domain.mappers

import com.githublagos.data.local.model.UserDetailLocal
import com.githublagos.domain.model.UserDetailDomain

fun UserDetailDomain.mapToLocal(): UserDetailLocal = UserDetailLocal(
    login = username,
    avatarUrl = avatarUrl,
    htmlUrl = githubUrl,
    name = name,
    location = location,
    followers = followers,
    following = following,
    repos = repos,
    bio = bio
)