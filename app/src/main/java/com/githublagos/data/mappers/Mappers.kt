package com.githublagos.data.mappers

import com.githublagos.data.model.UserDetailRemote
import com.githublagos.data.model.UserRemote
import com.githublagos.domain.model.UserDetailDomain
import com.githublagos.domain.model.UserDomain

fun UserDetailRemote.mapToDomain() = UserDetailDomain(
    username = login,
    avatarUrl = avatarUrl.orEmpty(),
    githubUrl = htmlUrl,
    name = name.orEmpty(),
    location = location.orEmpty(),
    followers = followers.safeInt(),
    following = following.safeInt(),
    repos = repos.safeInt(),
    bio = bio ?: "No Bio"
)

fun UserRemote.mapToDomain() = UserDomain(
    username = login,
    avatarUrl = avatarUrl,
    githubUrl = htmlUrl
)

fun Int?.safeInt(): Int = this ?: 0
