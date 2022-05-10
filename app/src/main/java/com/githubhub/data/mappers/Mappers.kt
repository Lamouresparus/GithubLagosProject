package com.githubhub.data.mappers

import com.githubhub.data.model.UserDetailRemote
import com.githubhub.data.model.UserRemote
import com.githubhub.domain.model.UserDetailDomain
import com.githubhub.domain.model.UserDomain

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
