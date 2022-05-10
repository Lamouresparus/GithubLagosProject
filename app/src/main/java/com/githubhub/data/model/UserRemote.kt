package com.githubhub.data.model

import com.google.gson.annotations.SerializedName

data class UserRemote(
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("html_url")
    val htmlUrl: String
)