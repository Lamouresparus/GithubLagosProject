package com.githubhub.domain

import com.githubhub.domain.model.UserDetailDomain
import com.githubhub.domain.model.UserDomain

interface GithubUserRepository {

    suspend fun getUsers(): List<UserDomain>

    suspend fun getUserDetail(username: String): UserDetailDomain
}