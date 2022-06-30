package com.githublagos.data.model

import com.google.gson.annotations.SerializedName

data class UserDetailRemote(
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("html_url")
    val htmlUrl: String,
    val name: String?,
    val location: String?,
    val followers: Int?,
    val following: Int?,
    @SerializedName("public_repos")
    val repos: Int?,
    val bio: String?
)