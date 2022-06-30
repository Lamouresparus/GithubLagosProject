package com.githublagos.ui.model

data class UserDetail(
    val username: String,
    val avatarUrl: String,
    val githubUrl: String,
    val name: String,
    val location: String,
    val followers: Int,
    val following: Int,
    val repos: Int,
    val bio: String
)
